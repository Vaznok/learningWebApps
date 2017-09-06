package com.vetallWebapp.listener.demo;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class MyHttpSessionActivationListener implements HttpSessionActivationListener {
    public MyHttpSessionActivationListener() {
        System.out.println(this.getClass().getSimpleName() + " NEW");
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println(">> HttpSession - will passive, id = " + se.getSession());
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println(">> HttpSession - did active, id = " + se.getSession());
    }

}
