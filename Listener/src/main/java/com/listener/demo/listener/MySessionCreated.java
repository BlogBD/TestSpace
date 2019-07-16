package com.listener.demo.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionCreated implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    System.out.println("创建了Session");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁了Session");
    }
}
