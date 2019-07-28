package cn.store.demo.dao.impl;

import cn.store.demo.dao.OrderDao;
import cn.store.demo.domain.Order;
import cn.store.demo.domain.OrderItem;
import cn.store.demo.domain.Product;
import cn.store.demo.domain.User;
import cn.store.demo.utils.JDBCUtils;
import cn.store.demo.utils.PageModel;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
  @Override
  public void saveOrder(Connection connection, Order order) throws SQLException {
    String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
    QueryRunner qr = new QueryRunner();
    Object[] params = {
      order.getOid(),
      order.getOrdertime(),
      order.getTotal(),
      order.getState(),
      order.getAddress(),
      order.getName(),
      order.getTelephone(),
      order.getUser().getUid()
    };
    qr.update(connection, sql, params);
  }

  @Override
  public void saveOrderItem(Connection connection, OrderItem item) throws SQLException {

    String sql = "insert into orderitem values(?,?,?,?,?)";
    QueryRunner qr = new QueryRunner();
    Object[] params = {
      item.getItemid(),
      item.getQuantity(),
      item.getTotal(),
      item.getProduct().getPid(),
      item.getOrder().getOid()
    };
    qr.update(connection, sql, params);
  }

  /**
   * 实现查询用户的所有订单总数
   *
   * @param user
   * @return
   * @throws SQLException
   */
  @Override
  public int getTotalRecords(User user) throws SQLException {
    String sql = "select count(*) from orders where uid=?";
    QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
    Long num = (Long) qr.query(sql, new ScalarHandler(), user.getUid());
    return num.intValue();
  }

  @Override
  public List<Order> findMyOrdersWithPage(User user, int startIndex, int pageSize)
      throws SQLException, InvocationTargetException, IllegalAccessException {
    List<Order> list = null;
    String sql = "select * from orders where uid=? limit ?,?";
    QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
    list =
        qr.query(sql, new BeanListHandler<Order>(Order.class), user.getUid(), startIndex, pageSize);
    // 遍历该用户的所有订单
    for (Order order : list) {
      // 获取到订单找到对应的订单项
      String oid = order.getOid();
      sql = "select * from orderitem o,product p where o.pid=p.pid and oid= ? ";
      List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), oid);
      // 遍历list
      setOrder(mapList, order);
    }
    return list;
  }

    /**
     * 通过oid查询到Order表
     * @param oid
     * @return
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
  @Override
  public Order findOrderByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException {
    String sql = "select * from orders where oid = ? ";
    QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
    Order order = qr.query(sql, new BeanHandler<>(Order.class), oid);
    sql="select * from orderitem o,product p where o.pid=p.pid and oid=?";
    List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), oid);
    setOrder(mapList,order);
    return order;
  }

    /**
     * 通关联表查询到的订单项和商品的关系，
     * 然后，通过BeanUtils.populate(）工具类，吧结果封装到订单项对象和商品对象中
     * @param mapList
     * @param order
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
  private void setOrder(List<Map<String, Object>> mapList, Order order)
      throws InvocationTargetException, IllegalAccessException {
    for (Map<String, Object> map : mapList) {
      OrderItem orderItem = new OrderItem();
      Product product = new Product();
      DateConverter dt = new DateConverter();
      dt.setPattern("yyyy-MM-dd");
      ConvertUtils.register(dt, java.util.Date.class);
      BeanUtils.populate(orderItem, map);
      BeanUtils.populate(product, map);
      orderItem.setProduct(product);
      order.getList().add(orderItem);
    }
  }
}
