package com.vetallWebapp.controller;

import com.vetallWebapp.entity.Product;
import com.vetallWebapp.eshop.dao.ProductDao;
import com.vetallWebapp.eshop.dao.exception.DaoException;
import com.vetallWebapp.eshop.dao.impl.jdbc.tx.TransactionBody;
import com.vetallWebapp.eshop.dao.impl.jdbc.tx.TransactionManager;
import com.vetallWebapp.inject.DependencyInjectionServlet;
import com.vetallWebapp.inject.Inject;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.vetallWebapp.helper.ClassName.getCurrentClassName;
import static org.apache.logging.log4j.LogManager.getLogger;


public class ProductControllerExternalTx extends DependencyInjectionServlet {
    static final String PARAM_ID = "id";
    static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    static final String PAGE_OK = "product.jsp";
    static final String PAGE_ERROR = "error.jsp";

    private static final Logger logger = getLogger(getCurrentClassName());

    @Inject("txManager")
    TransactionManager txManager;

    @Inject("productDao")
    ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        logger.trace("get parameter '" + PARAM_ID + "'");
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                logger.debug("parsed parameter '" + id + "'");
                Product model = txManager.doInTransaction(new TransactionBody<Product, DaoException>() {
                    @Override
                    public Product run() throws DaoException {
                        Product product = productDao.selectById(id);
                        logger.debug("Product: selectById(id) '" + product + "'");
                        return product;
                    }
                });
                req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
                logger.trace("set attribute '" + ATTRIBUTE_MODEL_TO_VIEW + "' " + model);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                logger.debug("PAGE_OK: RequestDispatcher.forward(...) to '" + PAGE_OK + "'");
                return;
            } catch (NumberFormatException | DaoException e) {
                //NOP
                resp.sendRedirect(PAGE_ERROR);
                logger.warn("PAGE_ERROR: sendRedirect(...) to '" + PAGE_ERROR + "' ", e);
            }
        }
        resp.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR:  sendRedirect(...) to '" + PAGE_ERROR + "' cause id == null");
    }
}
