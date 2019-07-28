package cn.store.demo.web.servlet;

import cn.store.demo.domain.*;
import cn.store.demo.service.OrderService;
import cn.store.demo.service.impl.OrderServiceImpl;
import cn.store.demo.utils.PageModel;
import cn.store.demo.utils.UUIDUtils;
import cn.store.demo.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(urlPatterns = "/OrderServlet")
public class OrderServlet extends BaseServlet {
  /**
   * 将购物车中的信息以订单的形式保存
   *
   * @param request
   * @param response
   * @return
   * @throws SQLException
   */
  public String saveOrder(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    // 确认用户登陆
    User user = (User) request.getSession().getAttribute("loginUser");
    if (null == user) {
      request.setAttribute("msg", "请登陆之后在下单！");
      return "/jsp/info.jsp";
    }
    // 获取购物车
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    // 创建订单对象，为订单对象赋值
    Order order = new Order();
    order.setOid(UUIDUtils.getCode());
    order.setOrdertime(new Date());
    order.setTotal(cart.getTotal());
    order.setState(1);
    order.setUser(user);
    for (CartItem item : cart.getCartItems()) {
      OrderItem orderItem = new OrderItem();
      orderItem.setItemid(UUIDUtils.getCode());
      orderItem.setQuantity(item.getNum());
      orderItem.setTotal(item.getSubTotal());
      orderItem.setProduct(item.getProduct());
      orderItem.setOrder(order);
      order.getList().add(orderItem);
    }
    // 创建订单项
    OrderService orderService = new OrderServiceImpl();
    orderService.saveOrder(order);
    // 清空购物车
    cart.clearCart();
    // 将订单放入request中
    request.setAttribute("order", order);
    // 调用业务层功能：保存订单
    return "/jsp/order_info.jsp";
  }

  /**
   * 查询订单
   *
   * @param request
   * @param response
   * @return
   */
  public String findMyOrderWithPage(HttpServletRequest request, HttpServletResponse response)
      throws SQLException, InvocationTargetException, IllegalAccessException {
    // 获取用户信息
    User user = (User) request.getSession().getAttribute("loginUser");
    // 获取当前页
    int num = Integer.parseInt(request.getParameter("num"));
    // 调用业务层，返回当前页的订单信息
    OrderService orderService = new OrderServiceImpl();
    PageModel pm = orderService.findMyOrderWithPage(user, num);
    // 吧PageModel放入request中
    request.setAttribute("page", pm);
    return "/jsp/order_list.jsp";
  }

  /**
   * 实现付款
    * @param request
   * @param response
   * @return
   */
  public String findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
    //获取付款订单的oid
    String oid = request.getParameter("oid");
    //调用业务层：根据订单编号查询到订单信息
    OrderService orderService = new OrderServiceImpl();
    Order order= orderService.findOrderByOid(oid);
    // 将订单放入request中
    request.setAttribute("order", order);
    // 调用业务层功能：保存订单
    return "/jsp/order_info.jsp";
  }
}
