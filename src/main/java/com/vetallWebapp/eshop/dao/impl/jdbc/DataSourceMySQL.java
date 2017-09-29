package com.vetallWebapp.eshop.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceMySQL extends BaseDataSource {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/vetall_list?" +
            "autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "qwerty";

    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        conn.setAutoCommit(false);
        return conn;
    }
}
