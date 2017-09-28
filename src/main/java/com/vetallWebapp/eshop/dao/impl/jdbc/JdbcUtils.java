package com.vetallWebapp.eshop.dao.impl.jdbc;

import java.sql.Connection;

public class JdbcUtils {
    public static void closeQuietly(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {/*NOP*/}
        }
    }

    public static void rollbackQuitely(Connection conn) {
        if(conn != null) {
            try {
                conn.rollback();
            } catch (Exception e) {/*NOP*/}
        }
    }

    public static void closeQuietly(AutoCloseable... resources) {
        for (AutoCloseable resource: resources) {
            closeQuietly(resource);
        }
    }
}
