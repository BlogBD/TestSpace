package cn.baseservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "ServletDemo01",urlPatterns = "/ServletDemo01")
public class ServletDemo01 extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String method = req.getParameter("method");
    if ("addStu".equals(method)) {
      System.out.println("添加学生");
    } else if ("delStu".equals(method)) {
      System.out.println("删除学生");
    } else if ("checkUser".equals(method)) {
      System.out.println("检测学生");
    }else if ("".equals(method)){

    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
