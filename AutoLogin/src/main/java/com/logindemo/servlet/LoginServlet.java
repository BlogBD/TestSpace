package com.logindemo.servlet;

import com.logindemo.bean.UserBean;
import com.logindemo.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
      String autologin = req.getParameter("auto_login");//获取用户登陆时，是否选择了自动登陆
      //封装获取到的登陆名和密码
      UserBean userBean = new UserBean();
      userBean.setUsername(username);
      userBean.setPassword(password);
      //验证登陆信息是否正确，如果用户验证成功，返回用户所有信息，失败者返回null
      UserBean loginUser = new UserDaoImpl().login(userBean);
      if(loginUser!=null){
          //页面提交上来时，判断是否选择了自动登陆
          if("on".equals(autologin)){
              //用户选择了下次自动登陆,发送cookie给客服端
              Cookie auto_login = new Cookie("auto_login", username + "#" + password);
              auto_login.setMaxAge(60*60*24*7);
              auto_login.setPath(req.getContextPath());
              resp.addCookie(auto_login);
          }
          req.getSession().setAttribute("user",loginUser);
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
