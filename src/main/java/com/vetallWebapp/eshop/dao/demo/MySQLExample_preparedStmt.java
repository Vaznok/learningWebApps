package com.vetallWebapp.eshop.dao.demo;

import com.vetallWebapp.entity.Product;
import com.vetallWebapp.eshop.dao.ProductDao;
import com.vetallWebapp.eshop.dao.exception.DaoSystemException;
import com.vetallWebapp.eshop.dao.exception.NoSuchEntityException;

import java.sql.*;
import java.util.List;

import static com.vetallWebapp.helper.Constants.USER;
import static com.vetallWebapp.helper.Constants.PASSWORD;
import static com.vetallWebapp.helper.Constants.JDBC_URL;

public class MySQLExample_preparedStmt implements ProductDao{
    private static final String SELECT_ALL_SQL = "SELECT id, caption, question FROM questions";
    private static final String  SELECT_BY_ID_SQL = "SELECT id, caption, question FROM questions WHERE id = ?";

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (!rs.next())
                throw new NoSuchEntityException("No product for id = " + id);
            Product result = new Product(rs.getInt("id"), rs.getString(""));
            conn.commit();
            return result;
        } catch (SQLException e) {
            com.vetallWebapp.eshop.dao.impl.jdbc.JdbcUtils.rollbackQuitely(conn);
            throw new DaoSystemException("Some exception", e);
        } finally {
            com.vetallWebapp.eshop.dao.impl.jdbc.JdbcUtils.closeQuietly(rs, stmt, conn);
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        return null;
    }

    public static void main(String[] args) {

    }
}
