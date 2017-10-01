package com.vetallWebapp.demo.jsf.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class Hello implements Serializable {
    private String message = "default";
    private String message2;
    private int count;
    private IntegerPair pair;

    public Hello() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public IntegerPair getPair() {
        return pair;
    }

    public void setPair(IntegerPair pair) {
        this.pair = pair;
    }
}
