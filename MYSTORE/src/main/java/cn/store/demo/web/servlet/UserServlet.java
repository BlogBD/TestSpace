package cn.store.demo.web.servlet;

import cn.store.demo.domain.User;
import cn.store.demo.service.UserService;
import cn.store.demo.service.impl.UserServiceImpl;
import cn.store.demo.utils.MailUtils;
import cn.store.demo.utils.MyBeanUtils;
import cn.store.demo.utils.UUIDUtils;
import cn.store.demo.web.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
  /**
   * 跳转到注册页面
   * @param request
   * @param response
   * @return
   */
  public String registUI(HttpServletRequest request,HttpServletResponse response){
    return "/jsp/register.jsp";
  }

  /**
   * 实现用户注册
   * @param request
   * @param response
   * @return
   */
  public String userRegister(HttpServletRequest request,HttpServletResponse response)  {
    //接收表单数据，封装数据
    User user =new User();
    Map<String, String[]> map = request.getParameterMap();
    MyBeanUtils.populate(user,map);//通过工具类自动封装
    //添加uid赋值
    user.setUid(UUIDUtils.getId());
    //设置状态码
    user.setState(0);
    //设置激活码
    user.setCode(UUIDUtils.getCode());
    //调用业务层注册功能
    UserService userService=new UserServiceImpl();
    try {
      userService.userRegist(user);
      //注册成功，向用户邮件发送信息，跳转到提示页面
      MailUtils.sendMail(user.getEmail(),user.getCode());
      //发送邮件
      request.setAttribute("msg","用户注册成功，请激活！");
    } catch (Exception e) {
      //注册失败，跳转到提示页面
      request.setAttribute("msg","用户注册失败，请重新注册！");
    }
    return "/jsp/info.jsp";
  }

  /**
   * 激活用户
   * @return
   */
  public String active(HttpServletRequest request,HttpServletResponse response){
    //获取激活码
    try {
      String code = request.getParameter("code");
      UserService userService = new UserServiceImpl();
      //激活查看是否有该激活码，查询到返回用户信息，未查询到抛出异常
      User user = userService.userActive(code);
      if (user!=null){
        //激活成功
        //1.修改该用户状态码设置为1
        user.setState(1);
        //2.清空激活码
        user.setCode("");
        //3.更新用户信息
        userService.updateUser(user);
      }
      request.setAttribute("msg","用户激活成功，请登陆");
      return "/jsp/login.jsp";
    } catch (SQLException e) {
      e.printStackTrace();
      request.setAttribute("msg","用户激活失败");
      return "/jsp/info.jsp";
    }
  }
}
