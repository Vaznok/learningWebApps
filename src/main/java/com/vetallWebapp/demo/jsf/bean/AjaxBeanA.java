package com.vetallWebapp.demo.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AjaxBeanA {
    private String message = "";

    public String getMessage() {
        return message;
    }

    public String getProcessMessage() {
        return "Hello " + message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
