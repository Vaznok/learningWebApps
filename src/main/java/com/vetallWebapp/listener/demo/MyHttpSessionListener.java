package com.vetallWebapp.listener.demo;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {
    public MyHttpSessionListener() {
        System.out.println(this.getClass().getSimpleName() + " NEW");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(">> HttpSession created " + se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println(">> HttpSession destroyed " + se.getSession());
    }
}
