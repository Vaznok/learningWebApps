package com.vetallWebapp.eshop.dao.impl.jdbc.tx;

import com.vetallWebapp.eshop.dao.impl.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TransactionManagerImpl extends BaseDataSource implements TransactionManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/vetall_list?" +
            "autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "vetall";
    private static final String PASSWORD = "qwerty";
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    @Override
    public <T> T doInTransaction(Callable<T> unitOfWork) throws Exception {
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        conn.setAutoCommit(false);
        connectionHolder.set(conn);
        try {
            T result = unitOfWork.call();
            conn.commit();
            return result;
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            JdbcUtils.closeQuietly(conn);
            connectionHolder.remove();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionHolder.get();
    }
}
