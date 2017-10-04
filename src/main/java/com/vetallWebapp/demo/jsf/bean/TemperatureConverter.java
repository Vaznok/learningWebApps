package com.vetallWebapp.demo.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class TemperatureConverter {
    private String value = "";

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getKValue() {
        return value + "K";
    }

    public String getCValue() {
        return value + "C";
    }
}
