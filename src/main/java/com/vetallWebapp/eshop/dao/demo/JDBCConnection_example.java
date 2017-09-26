package com.vetallWebapp.eshop.dao.demo;

import java.sql.*;

public class JDBCConnection_example {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/vetall_list?" +
                    "autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "vetall";
    private static final String PASSWORD = "qwerty";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Driver driver;

        try   {
            driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        }
        catch (SQLException e1) {
            System.out.println("Драйвер не зарегистрировался");
            return;
        }
        try {
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            if (!connection.isClosed())
                System.out.println("Соединение установлено");
            connection.setAutoCommit(false);
            Statement stmt = connection.createStatement();
            stmt.execute("DROP TABLE IF EXISTS tmp;");
            stmt.execute("CREATE TABLE tmp (id INT, name VARCHAR(64))");
            stmt.execute("INSERT INTO tmp (id, name) VALUES (1, 'Petya')");

            ResultSet rs = stmt.executeQuery("SELECT id, name FROM tmp");
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.printf("%d, %s\n", id, name);
            }

        }catch (SQLException ex){
            System.err.println("Соединение не установлено");
        } finally {
            if (connection != null) connection.close();
        }
    }
}
