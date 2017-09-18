package com.vetallWebapp.helper;

import java.sql.Connection;

public class JdbcUtils {
    public static void rollbackQuietly(Connection conn) {
        if(conn != null) {
            try{
                conn.rollback();
            } catch (Exception ignore) {/*NOP*/}
        }
    }
    public static void closeQuietly (AutoCloseable... resources){
        for (AutoCloseable resource: resources) {
            closeQuietly(resource);
        }
    }
    public static void closeQuietly (AutoCloseable resource){
        if(resource != null) {
            try {
                resource.close();
            } catch (Exception e) {/*NOP*/}
        }
    }
}
