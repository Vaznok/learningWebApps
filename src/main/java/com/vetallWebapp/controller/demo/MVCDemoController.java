package com.vetallWebapp.controller.demo;

import com.vetallWebapp.entity.DemoEntityA;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MVCDemoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //add to REQUEST attributes
        req.setAttribute("requestAttribute", new DemoEntityA());
        //add to SESSION attributes
        req.setAttribute("sessionAttribute", new DemoEntityA());
        //add to SERVLET_CONTEXT attributes
        req.setAttribute("servletContextAttribute", new DemoEntityA());

        req.setAttribute("test", "request");
        req.getSession().setAttribute("test", "session");
        req.getServletContext().setAttribute("test", "servletContext");

        req.getRequestDispatcher("mvcDemoView.jsp").forward(req, resp);
    }
}
