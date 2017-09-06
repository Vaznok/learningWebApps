package com.vetallWebapp.listener.demo;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {
    public MyServletContextAttributeListener() {
        System.out.println(this.getClass().getSimpleName() + " NEW");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println(">> ServletContext - attributeAdded " + event.getServletContext());

}
    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println(">> ServletContext - attributeRemoved " + event.getServletContext());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println(">> ServletContext - attributeReplaced " + event.getServletContext());
    }
}
