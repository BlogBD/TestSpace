package com.listener.demo.dao.domain;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Bean01 implements HttpSessionBindingListener {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
    System.out.println("值被绑定");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("解除绑定");

    }
}
