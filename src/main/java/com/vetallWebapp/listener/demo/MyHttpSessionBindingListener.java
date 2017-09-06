package com.vetallWebapp.listener.demo;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class MyHttpSessionBindingListener implements HttpSessionBindingListener {
    public MyHttpSessionBindingListener() {
        System.out.println(this.getClass().getSimpleName() + " NEW");
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println(">> HttpSession valueBound " + event.getSession());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println(">> HttpSession valueUnbound " + event.getSession());
    }
}
