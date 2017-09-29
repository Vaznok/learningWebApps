package com.vetallWebapp.eshop.dao.impl.jdbc;

import com.vetallWebapp.entity.Product;
import com.vetallWebapp.eshop.dao.ProductDao;
import com.vetallWebapp.eshop.dao.exception.DaoSystemException;
import com.vetallWebapp.eshop.dao.exception.NoSuchEntityException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcExternalTxImpl implements ProductDao {
    public static final String SELECT_ALL_SQL = "SELECT id, name FROM Products";
    public static final String SELECT_BY_ID_SQL = "SELECT id, name FROM Products WHERE id = ?";

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Connection conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("No product for id = " + id);
            }
            Product result = new Product(rs.getInt("id"), rs.getString("name"));
            return result;
        } catch (SQLException e) {
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(rs, stmt);
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Connection conn = dataSource.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SELECT_ALL_SQL);
            List<Product> list = new ArrayList<>();
            if (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name")));
            }
            return list;
        } catch (SQLException e) {
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(rs, stmt);
        }
    }
}
