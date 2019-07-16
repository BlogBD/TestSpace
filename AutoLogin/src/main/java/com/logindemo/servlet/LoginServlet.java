package com.logindemo.servlet;

import com.logindemo.bean.UserBean;
import com.logindemo.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
      String username = req.getParameter("username");
      String password = req.getParameter("password");
      String autologin = req.getParameter("auto_login");
      UserBean userBean = new UserBean();
      userBean.setUsername(username);
      userBean.setPassword(password);
      UserBean loginUser = new UserDaoImpl().login(userBean);
      if(loginUser!=null){
          //成功
          resp.sendRedirect("successful.jsp");
      }else{
          //不成功,回到登陆页
          req.getRequestDispatcher("login.jsp").forward(req,resp);
      }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doGet(req, resp);
  }
}
