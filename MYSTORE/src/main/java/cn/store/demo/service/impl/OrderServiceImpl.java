package cn.store.demo.service.impl;

import cn.store.demo.dao.OrderDao;
import cn.store.demo.dao.impl.OrderDaoImpl;
import cn.store.demo.domain.Order;
import cn.store.demo.domain.OrderItem;
import cn.store.demo.service.OrderService;
import cn.store.demo.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderServiceImpl implements OrderService {
  /**
   * 保存订单的业务
   *
   * @param order
   */
  @Override
  public void saveOrder(Order order) throws  Exception{
    Connection conn = null;
    try {
      conn = JDBCUtils.getConnection();
      // 开启事务
      conn.setAutoCommit(false);
      // 保存订单
      OrderDao orderDao = new OrderDaoImpl();
      orderDao.saveOrder(conn,order);
      // 保存订单项
      for (OrderItem item : order.getList()) {
        orderDao.saveOrderItem(conn,item);
      }
      //提交
        conn.commit();
    } catch (SQLException e) {
      // 回滚事务
        conn.rollback();
    }finally{
        if (null!=conn){
            conn.close();
            conn=null;
        }
    }
  }
}
