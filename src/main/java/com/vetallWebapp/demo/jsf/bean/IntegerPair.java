package com.vetallWebapp.demo.jsf.bean;

public class IntegerPair {
    private int fst;
    private int scd;

    public IntegerPair(int fst, int scd) {
        this.fst = fst;
        this.scd = scd;
    }

    public int getFst() {
        return fst;
    }

    public void setFst(int fst) {
        this.fst = fst;
    }

    public int getScd() {
        return scd;
    }

    public void setScd(int scd) {
        this.scd = scd;
    }

    @Override
    public String toString() {
        return fst + "-" + scd;
    }
}
