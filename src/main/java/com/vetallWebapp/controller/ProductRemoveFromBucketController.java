package com.vetallWebapp.controller;

import com.vetallWebapp.dao.ProductDao;
import com.vetallWebapp.dao.exception.DaoSystemException;
import com.vetallWebapp.dao.exception.NoSuchEntityException;
import com.vetallWebapp.dao.impl.ProductDaoMock;
import com.vetallWebapp.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.vetallWebapp.helper.SessionAttributes.PRODUCTS_IN_BUCKET;
import static java.util.Collections.unmodifiableMap;

public class ProductRemoveFromBucketController extends HttpServlet {
    private static final String PARAM_ID = "id";
    private static final String PAGE_ERROR = "error.jsp";

    private ProductDao productDao = new ProductDaoMock();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Product product = productDao.selectById(id);

                HttpSession session = req.getSession();
                Map<Product, Integer> oldBucket = (Map<Product, Integer>) session.getAttribute(PRODUCTS_IN_BUCKET);
                if (oldBucket.size() > 0) {
                    Map<Product, Integer> newBucket = new LinkedHashMap<>(oldBucket);
                    Integer productCount = newBucket.get(product);
                    if (newBucket.containsKey(product) && productCount > 1) {
                        newBucket.put(product, productCount - 1);
                    } else if (newBucket.containsKey(product)) {
                        newBucket.remove(product);
                    }
                    session.setAttribute(PRODUCTS_IN_BUCKET, unmodifiableMap(newBucket));
                    String newLocation = "product.do?id=" + id;
                    resp.sendRedirect(newLocation);
                    return;
                }
            } catch (NumberFormatException | NoSuchEntityException | DaoSystemException ignore) {
                //NOP
            }
            resp.sendRedirect(PAGE_ERROR);
        }
    }
}
