package cn.store.demo.service.impl;

import cn.store.demo.dao.OrderDao;
import cn.store.demo.dao.impl.OrderDaoImpl;
import cn.store.demo.domain.Order;
import cn.store.demo.domain.OrderItem;
import cn.store.demo.domain.User;
import cn.store.demo.service.OrderService;
import cn.store.demo.utils.JDBCUtils;
import cn.store.demo.utils.PageModel;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
  /**
   * 保存订单的业务
   *
   * @param order
   */
  @Override
  public void saveOrder(Order order) throws Exception {
    Connection conn = null;
    try {
      conn = JDBCUtils.getConnection();
      // 开启事务
      conn.setAutoCommit(false);
      // 保存订单
      OrderDao orderDao = new OrderDaoImpl();
      orderDao.saveOrder(conn, order);
      // 保存订单项
      for (OrderItem item : order.getList()) {
        orderDao.saveOrderItem(conn, item);
      }
      // 提交
      conn.commit();
    } catch (SQLException e) {
      System.out.println("》》》》》》》》》》》》》订单存储失败》》》》》》》》》》》》》》");
      // 回滚事务
      conn.rollback();
    } finally {
      if (null != conn) {
        conn.close();
        conn = null;
      }
    }
  }

  /**
   * 实现订单查询业务
   * @param user
   * @param num
   * @return
   */
  @Override
  public PageModel findMyOrderWithPage(User user, int num) throws SQLException, InvocationTargetException, IllegalAccessException {

    PageModel pm= null;
    try {
      //获取中的该用户的所有订单总数
      int total= new OrderDaoImpl().getTotalRecords(user);
      //1.创建pagemodel对象，目的：计算并且携带分页参数
      pm = new PageModel(num,total,3);
      //2.关联集合
      List<Order> list=new OrderDaoImpl().findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
      pm.setList(list);
      //3.关联url
      pm.setUrl("OrderServlet?method=findMyOrderWithPage");
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return pm;
  }

  @Override
  public Order findOrderByOid(String oid) throws SQLException {

    return new OrderDaoImpl().findOrderByOid(oid);
  }
}
