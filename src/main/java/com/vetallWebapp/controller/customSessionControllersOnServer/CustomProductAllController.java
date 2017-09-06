package com.vetallWebapp.controller.customSessionControllersOnServer;

import com.vetallWebapp.dao.ProductDao;
import com.vetallWebapp.dao.exception.DaoSystemException;
import com.vetallWebapp.dao.impl.ProductDaoMock;
import com.vetallWebapp.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomProductAllController extends HttpServlet {
    private static final String ATTRIBUTE_MODEL_TO_VIEW = "productList";
    private static final String PAGE_OK = "productAllCustomSession.jsp";
    private static final String PAGE_ERROR = "error.jsp";

    private ProductDao productDao = new ProductDaoMock();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Product> models = productDao.selectAll();
            req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, models);
            RequestDispatcher dispatcher = req.getRequestDispatcher(PAGE_OK);
            dispatcher.forward(req, resp);
            return;
        } catch (DaoSystemException ignore) {
            //NOP
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
