package com.vetallWebapp.inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextHolder {
    private static final Map<String, ApplicationContext> pathToContextRepository = new HashMap<>();

    static synchronized ApplicationContext getClassPathXmlApplicationContext(String path) {
        if(!pathToContextRepository.containsKey(path)) {
            pathToContextRepository.put(path, new ClassPathXmlApplicationContext());
        }
        return pathToContextRepository.get(path);
    }
}
