package cn.store.demo.web.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目的基类
 *
 * 继承该类的所有servlet会在请求该servlet是时候提供一个method参数，
 * 在service方法中，通过发射去调用method给发参数值，也就是子类要实现的方法，
 * 从而达到减少servlet的效果
 */
@WebServlet(name = "BaseServlet", urlPatterns = "/BaseServlet")
public class BaseServlet extends HttpServlet {
  @Override
  public void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // localhost:8080/store/productServlet?method=addProduct/
    String method = req.getParameter("method"); // 获取到传过来的请方法名

    if (null == method || "".equals(method) || method.trim().equals("")) {
      method = "execute";
    }

    // 注意:此处的this代表的是子类的对象
    // System.out.println(this);
    // 子类对象字节码对象
    Class clazz = this.getClass();

    try {
      // 查找子类对象对应的字节码中的名称为method的方法.这个方法的参数类型是:HttpServletRequest.class,HttpServletResponse.class
      Method md = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
      if (null != md) {
        String jspPath = (String) md.invoke(this, req, resp);//执行传入的方法名对应的方法
        if (null != jspPath) {
          req.getRequestDispatcher(jspPath).forward(req, resp);
        }
      }
    } catch (Exception e) {
      System.err.println("反射错误");
      e.printStackTrace();
    }
  }

  // 默认方法
  public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
    return "/index.jsp";
  }
}
