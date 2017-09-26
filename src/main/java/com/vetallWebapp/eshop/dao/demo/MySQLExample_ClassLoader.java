package com.vetallWebapp.eshop.dao.demo;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MySQLExample_ClassLoader {
    public static void main(String[] args) throws IOException {
        //also working option
        /*ClassLoader loader
                = Thread.currentThread().getContextClassLoader();*/
        ClassLoader loader
                = MySQLExample_ClassLoader.class.getClassLoader();
        Enumeration<URL> drivers
                = loader.getResources("META-INF/services/java.sql.Driver");
        while(drivers.hasMoreElements()) {
            System.out.println(drivers.nextElement());
            System.out.println();
        }
    }

}
