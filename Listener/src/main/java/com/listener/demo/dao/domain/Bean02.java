package com.listener.demo.dao.domain;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import java.io.Serializable;

public class Bean02 implements HttpSessionActivationListener , Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
    System.err.println("session中的值被序列化了");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
    System.err.println("session中的值被反序列化了");
    }
}
