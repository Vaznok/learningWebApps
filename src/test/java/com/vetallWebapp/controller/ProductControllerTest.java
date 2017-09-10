package com.vetallWebapp.controller;

import com.vetallWebapp.entity.Product;
import com.vetallWebapp.eshop.dao.ProductDao;
import com.vetallWebapp.eshop.dao.exception.DaoSystemException;
import com.vetallWebapp.eshop.dao.exception.NoSuchEntityException;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.vetallWebapp.controller.ProductController.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {
    private ProductController productController;

    @Before
    public void setUp() {
        this.productController = new ProductController();
    }
    @Test
    public void test_request_no_param() throws ServletException, IOException {
        //init
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter(PARAM_ID)).thenReturn(null);
        //use
        productController.doGet(req, resp);

        //check
        verify(req).getParameter(PARAM_ID);
        verify(resp).sendRedirect(PAGE_ERROR);
        verifyNoMoreInteractions(req, resp);
    }

    @Test
    public void test_request_bad_param() throws ServletException, IOException {
        //init
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter(PARAM_ID)).thenReturn("abc");
        //use
        productController.doGet(req, resp);

        //check
        verify(req).getParameter(PARAM_ID);
        verify(resp).sendRedirect(PAGE_ERROR);
        verifyNoMoreInteractions(req, resp);
    }

    @Test
    public void test_request_no_in_dao() throws ServletException, IOException, DaoSystemException, NoSuchEntityException {
        //init
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ProductDao dao = mock(ProductDao.class);

        when(req.getParameter(PARAM_ID)).thenReturn("123");
        when(dao.selectById(anyInt())).thenThrow(new NoSuchEntityException(""));

        //use
        productController.productDao = dao;
        productController.doGet(req, resp);

        //check
        verify(req).getParameter(PARAM_ID);
        verify(dao).selectById(123);
        verify(resp).sendRedirect(PAGE_ERROR);
        verifyNoMoreInteractions(req, dao, resp);

    }

    @Test
    public void test_request_dao_crashed() throws ServletException, IOException, DaoSystemException, NoSuchEntityException {
        //init
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ProductDao dao = mock(ProductDao.class);

        when(req.getParameter(PARAM_ID)).thenReturn("123");
        when(dao.selectById(anyInt())).thenThrow(new DaoSystemException(""));

        //use
        productController.productDao = dao;
        productController.doGet(req, resp);

        //check
        verify(req).getParameter(PARAM_ID);
        verify(dao).selectById(123);
        verify(resp).sendRedirect(PAGE_ERROR);
        verifyNoMoreInteractions(req, dao, resp);

    }

    @Test
    public void test_ok() throws ServletException, IOException, DaoSystemException, NoSuchEntityException {
        //init
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        ProductDao dao = mock(ProductDao.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Product product = new Product(123, "SomeName");

        when(req.getParameter(PARAM_ID)).thenReturn("123");
        when(dao.selectById(anyInt())).thenReturn(product);
        when(req.getRequestDispatcher(PAGE_OK)).thenReturn(dispatcher);

        //use
        productController.productDao = dao;
        productController.doGet(req, resp);

        //check
        verify(req).getParameter(PARAM_ID);
        verify(dao).selectById(123);
        verify(req).setAttribute(ATTRIBUTE_MODEL_TO_VIEW, product);
        verify(req).getRequestDispatcher(PAGE_OK);
        verify(dispatcher).forward(req, resp);
        verifyNoMoreInteractions(req, resp, dao, dispatcher);
    }

}
