package com.listener.demo.listener02;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MyHttpSession02 implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
    System.out.println("属性添加进来了");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("属性移除了");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("属性被替换了");
    }
}
