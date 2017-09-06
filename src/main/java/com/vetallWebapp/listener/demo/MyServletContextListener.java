package com.vetallWebapp.listener.demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    public MyServletContextListener() {
        System.out.println(this.getClass().getSimpleName() + " NEW");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(">> ServletContext initialized " + sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(">> ServletContext destroyed " + sce.getServletContext());
    }

}
