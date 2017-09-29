package com.vetallWebapp.eshop.dao.impl.jdbc.tx;

public interface TransactionManager {
    <T, E extends Exception> T doInTransaction (TransactionBody<T, E> unitOfWork) throws E;
}

