package com.listener.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    System.out.println("servletContext初始化了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("servletContext销毁了");

    }
}
