package com.vetallWebapp.controller.demo;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class CookieDemoController extends HttpServlet {
    private static final String COOKIE_NAME = "counter";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        Cookie cookieFromClient = null;

        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if(cookie.getName().equals(COOKIE_NAME)) {
                    cookieFromClient = cookie;
                    break;
                }
            }
        }

        if (cookieFromClient == null) {
            cookieFromClient = new Cookie(COOKIE_NAME, "" + 1);
            resp.addCookie(cookieFromClient);
            resp.getWriter().write("You visit this page 1 time");
        } else {
            int visitCount = Integer.valueOf(cookieFromClient.getValue());
            resp.addCookie(new Cookie(COOKIE_NAME, ++visitCount + ""));
            resp.getWriter().write("You visit this page " + visitCount + " time");
        }
    }
}
