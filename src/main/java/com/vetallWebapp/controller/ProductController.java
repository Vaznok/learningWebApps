package com.vetallWebapp.controller;

import com.vetallWebapp.eshop.dao.ProductDao;
import com.vetallWebapp.entity.Product;
import com.vetallWebapp.eshop.dao.exception.DaoSystemException;
import com.vetallWebapp.eshop.dao.exception.NoSuchEntityException;
import com.vetallWebapp.inject.DependencyInjectionServlet;
import com.vetallWebapp.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductController extends DependencyInjectionServlet {
    private static final String PARAM_ID = "id";
    private static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    private static final String PAGE_OK = "product.jsp";
    private static final String PAGE_ERROR = "error.jsp";

    @Inject("productDao")
    private ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Product model = productDao.selectById(id);
                req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (NumberFormatException | NoSuchEntityException | DaoSystemException e) {
                //NOP
            }
            resp.sendRedirect(PAGE_ERROR);
        }
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
