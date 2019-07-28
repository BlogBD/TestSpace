package cn.store.demo.dao;

import cn.store.demo.domain.Order;
import cn.store.demo.domain.OrderItem;
import cn.store.demo.domain.User;
import cn.store.demo.utils.PageModel;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    /**
     * 保存订单
     * @param connection
     * @param order
     * @throws SQLException
     */
    void saveOrder(Connection connection, Order order) throws SQLException;

    /**
     * 保存订单项
     * @param connection
     * @param item
     * @throws SQLException
     */
    void saveOrderItem(Connection connection,OrderItem item) throws SQLException;

    /**
     * 获取用户的总订单数
     * @param user
     * @return
     */
    int getTotalRecords(User user) throws SQLException;

    /**
     * 分页查询获取该页的订单信息
     * @param user
     * @param startIndex
     * @param pageSize
     */
    List<Order> findMyOrdersWithPage(User user, int startIndex, int pageSize) throws SQLException, InvocationTargetException, IllegalAccessException;

    /**
     * 通过订单表oid查询到订单表
     * @param oid
     * @return
     */
    Order findOrderByOid(String oid) throws SQLException, InvocationTargetException, IllegalAccessException;
}
