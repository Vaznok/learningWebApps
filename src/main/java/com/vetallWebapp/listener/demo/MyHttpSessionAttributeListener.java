package com.vetallWebapp.listener.demo;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    public MyHttpSessionAttributeListener() {
        System.out.println(this.getClass().getSimpleName() + " NEW");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println(">> HttpSession - attributeAdded " + event.getSession());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println(">> HttpSession - attributeRemoved " + event.getSession());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println(">> HttpSession - attributeReplaced " + event.getSession());
    }
}
