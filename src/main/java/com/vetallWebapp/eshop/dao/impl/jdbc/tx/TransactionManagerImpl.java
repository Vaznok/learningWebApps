package com.vetallWebapp.eshop.dao.impl.jdbc.tx;

import com.vetallWebapp.eshop.dao.impl.jdbc.BaseDataSource;
import com.vetallWebapp.eshop.dao.impl.jdbc.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerImpl extends BaseDataSource implements TransactionManager {
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();
    DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <T, E extends Exception> T doInTransaction(TransactionBody<T, E> unitOfWork) {
        Connection conn = null;
        T result = null;
        try {
        conn = dataSource.getConnection();
        connectionHolder.set(conn);
        result = unitOfWork.run();
        conn.commit();
        return result;
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException sql) {
                sql.getMessage();
            }

        } finally {
            JdbcUtils.closeQuietly(conn);
            connectionHolder.remove();
        }
        return result;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionHolder.get();
    }
}
