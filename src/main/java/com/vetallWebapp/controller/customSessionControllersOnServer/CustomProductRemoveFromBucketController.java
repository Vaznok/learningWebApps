package com.vetallWebapp.controller.customSessionControllersOnServer;

import com.vetallWebapp.custom_view_framework.CustmoHttpSessionOnServerRepository;
import com.vetallWebapp.custom_view_framework.CustomHttpSession;
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
import static com.vetallWebapp.helper.SessionAttributes.PRODUCTS_IN_BUCKET2;
import static java.util.Collections.unmodifiableMap;

public class CustomProductRemoveFromBucketController extends HttpServlet {
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

                HttpSession session = req.getSession(true);
                CustomHttpSession customSession = CustmoHttpSessionOnServerRepository.getSession(session.getId());

                Map<Product, Integer> oldBucket = (Map<Product, Integer>) customSession.getAttribute(PRODUCTS_IN_BUCKET2);
                if (oldBucket.size() > 0) {
                    Map<Product, Integer> newBucket = new LinkedHashMap<>(oldBucket);
                    Integer productCount = newBucket.get(product);
                    if (newBucket.containsKey(product) && productCount > 1) {
                        newBucket.put(product, productCount - 1);
                    } else if (newBucket.containsKey(product)) {
                        newBucket.remove(product);
                    }
                    customSession.putAttribute(PRODUCTS_IN_BUCKET2, unmodifiableMap(newBucket));
                    session.setAttribute(PRODUCTS_IN_BUCKET2, customSession.getAttribute(PRODUCTS_IN_BUCKET2));
                    //req.setAttribute(PRODUCTS_IN_BUCKET2, customSession.getAttribute(PRODUCTS_IN_BUCKET2));
                    String newLocation = "productCustomSession.se?id=" + id;
                    //req.getRequestDispatcher(newLocation).forward(req, resp);
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
