package com.vetallWebapp.listener.demo;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener {
    public MyServletRequestListener() {
        System.out.println(this.getClass().getSimpleName() + " NEW");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("ServletRequest destroyed " + sre.getServletContext());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("ServletRequest initialized " + sre.getServletContext());
    }

}
