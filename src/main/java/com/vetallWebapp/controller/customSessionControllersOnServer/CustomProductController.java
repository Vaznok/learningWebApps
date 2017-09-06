package com.vetallWebapp.controller.customSessionControllersOnServer;

import com.vetallWebapp.dao.ProductDao;
import com.vetallWebapp.dao.exception.DaoSystemException;
import com.vetallWebapp.dao.exception.NoSuchEntityException;
import com.vetallWebapp.dao.impl.ProductDaoMock;
import com.vetallWebapp.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomProductController extends HttpServlet {
    private static final String PARAM_ID = "id";
    private static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    private static final String PAGE_OK = "productCustomSession.jsp";
    private static final String PAGE_ERROR = "error.jsp";

    private ProductDao productDao = new ProductDaoMock();

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
}