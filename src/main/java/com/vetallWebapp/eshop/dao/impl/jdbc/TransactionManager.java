package com.vetallWebapp.eshop.dao.impl.jdbc;

import java.util.concurrent.Callable;

public interface TransactionManager {
    <T> T doInTransaction(Callable<T> unitOfWork) throws Exception;
}
