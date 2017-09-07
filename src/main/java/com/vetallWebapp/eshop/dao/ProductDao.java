package com.vetallWebapp.eshop.dao;

import com.vetallWebapp.entity.Product;
import com.vetallWebapp.eshop.dao.exception.DaoSystemException;
import com.vetallWebapp.eshop.dao.exception.NoSuchEntityException;

import java.util.List;

public interface ProductDao {
    Product selectById(int id) throws DaoSystemException, NoSuchEntityException;
    List<Product> selectAll() throws DaoSystemException;
}
