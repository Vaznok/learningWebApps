package com.vetallWebapp.listener.demo;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

public class MyRequestContextAttributeListener implements ServletRequestAttributeListener {
    public MyRequestContextAttributeListener() {
        System.out.println(this.getClass().getSimpleName() + " NEW");
    }


    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println(">> ServletRequest - attributeAdded " + srae.getServletContext());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println(">> ServletRequest - attributeRemoved " + srae.getServletContext());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println(">> ServletRequest - attributeReplaced " + srae.getServletContext());
    }
}
