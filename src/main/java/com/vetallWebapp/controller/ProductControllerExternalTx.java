package com.vetallWebapp.controller;

import com.vetallWebapp.entity.Product;
import com.vetallWebapp.eshop.dao.ProductDao;
import com.vetallWebapp.eshop.dao.exception.DaoException;
import com.vetallWebapp.eshop.dao.impl.jdbc.tx.TransactionBody;
import com.vetallWebapp.eshop.dao.impl.jdbc.tx.TransactionManager;
import com.vetallWebapp.inject.DependencyInjectionServlet;
import com.vetallWebapp.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductControllerExternalTx extends DependencyInjectionServlet {
    static final String PARAM_ID = "id";
    static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    static final String PAGE_OK = "product.jsp";
    static final String PAGE_ERROR = "error.jsp";

    @Inject("txManager")
    TransactionManager txManager;

    @Inject("productDao")
    ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Product model = txManager.doInTransaction(new TransactionBody<Product, DaoException>() {
                    @Override
                    public Product run() throws DaoException {
                        return productDao.selectById(id);
                    }
                });
                req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (NumberFormatException | DaoException e) {
                //NOP
            }
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
