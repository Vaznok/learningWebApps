package com.vetallWebapp.eshop.dao.impl.jdbc.tx;

public interface TransactionBody<T, E extends Exception> {
    <T> T run() throws E;
}

