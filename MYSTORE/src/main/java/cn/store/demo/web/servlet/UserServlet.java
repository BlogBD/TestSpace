package cn.store.demo.web.servlet;

import cn.store.demo.domain.User;
import cn.store.demo.service.UserService;
import cn.store.demo.service.impl.UserServiceImpl;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "UserServlet", urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {
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
      //发送邮件
      request.setAttribute("msg","用户注册成功，请激活！");
    } catch (Exception e) {
      //注册失败，跳转到提示页面
      request.setAttribute("msg","用户注册失败，请重新注册！");
    }
    return "/jsp/info.jsp";
  }
}
