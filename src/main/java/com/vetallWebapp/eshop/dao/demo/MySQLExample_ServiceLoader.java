package com.vetallWebapp.eshop.dao.demo;

import javax.sound.sampled.spi.AudioFileReader;
import java.nio.charset.spi.CharsetProvider;
import java.sql.Connection;
import java.sql.Driver;
import java.util.ServiceLoader;


public class MySQLExample_ServiceLoader {

    public static void main(String[] args) {
        ServiceLoader<Driver> drivers = ServiceLoader.load(Driver.class);
        for (Driver driver: drivers) {
            System.out.println(driver);
        }
        System.out.println();
        ServiceLoader<AudioFileReader> audioFileReaders = ServiceLoader.load(AudioFileReader.class);
        for (AudioFileReader audio: audioFileReaders) {
            System.out.println(audio);
        }
    }
}
