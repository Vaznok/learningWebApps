package com.vetallWebapp.dao;

import com.vetallWebapp.entity.Product;
import com.vetallWebapp.dao.exception.DaoSystemException;
import com.vetallWebapp.dao.exception.NoSuchEntityException;

import java.util.List;

public interface ProductDao {
    Product selectById(int id) throws DaoSystemException, NoSuchEntityException;
    List<Product> selectAll() throws DaoSystemException;
}
