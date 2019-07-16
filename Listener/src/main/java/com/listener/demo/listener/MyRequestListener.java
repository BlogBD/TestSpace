package com.listener.demo.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
    System.out.println("servletRequest销毁了");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("servletRequest创建了");
    }
}
