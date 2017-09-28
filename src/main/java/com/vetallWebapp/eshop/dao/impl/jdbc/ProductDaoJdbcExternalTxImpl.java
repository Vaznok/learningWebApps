package com.vetallWebapp.eshop.dao.impl.jdbc;

import com.vetallWebapp.entity.Product;
import com.vetallWebapp.eshop.dao.ProductDao;
import com.vetallWebapp.eshop.dao.exception.DaoSystemException;
import com.vetallWebapp.eshop.dao.exception.NoSuchEntityException;

import javax.sql.DataSource;
import java.util.List;

public class ProductDaoJdbcExternalTxImpl implements ProductDao {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/vetall_list?" +
            "autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "qwerty";

    public static final String SELECT_ALL_SQL = "SELECT id, ";
    public static final String SELECT_BY_ID_SQL = "SELECT ";

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        return null;
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        return null;
    }
}
