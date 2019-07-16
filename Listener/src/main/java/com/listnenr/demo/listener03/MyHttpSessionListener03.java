package com.listnenr.demo.listener03;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class MyHttpSessionListener03 implements HttpSessionActivationListener {
  // 序列化
  @Override
  public void sessionWillPassivate(HttpSessionEvent se) {
    System.out.println("session被序列化了");
  }

  // 反序列化
  @Override
  public void sessionDidActivate(HttpSessionEvent se) {
    System.out.println("session 被序列化了");
  }
}
