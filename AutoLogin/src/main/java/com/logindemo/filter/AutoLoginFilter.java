package com.logindemo.filter;

import com.logindemo.bean.UserBean;
import com.logindemo.dao.UserDao;
import com.logindemo.dao.impl.UserDaoImpl;
import com.logindemo.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "AutoLoginFilter", urlPatterns = "/*")
public class AutoLoginFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    // 判断session中还有没有用户信息
    UserBean user = (UserBean) request.getSession().getAttribute("user");
    // 如果还存在用户信息直接放行
    if (user != null) {
      chain.doFilter(req, resp);
    } else // session中存入的用户信息失效了 ，从cookie中获取
    {
      // 获取cookie数组
      Cookie[] cookies = request.getCookies();
      // 找到客服端的cookie
      Cookie auto_loginCookie = CookieUtil.findCookieByName(cookies, "auto_login");
      // 未找到cookie直接放行
      if (auto_loginCookie == null) {
        chain.doFilter(req, resp);
      } else // 存入了cookie值，可以自动登陆了
      {
        String value = auto_loginCookie.getValue();
        String[] split = value.split("#");
        // 通过cookie获取到登陆信息
        String username = split[0];
        String password = split[1];
        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);
        // 登陆一遍
        UserDao dao = new UserDaoImpl();
        UserBean login = dao.login(userBean);
        // 存入session中
        request.getSession().setAttribute("user", login);
        chain.doFilter(req, resp);
      }
    }
  }

  @Override
  public void destroy() {}
}
