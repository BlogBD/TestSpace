package cn.store.demo.dao;

import cn.store.demo.domain.Order;
import cn.store.demo.domain.OrderItem;

import java.sql.Connection;

public interface OrderDao {
    void saveOrder(Connection connection, Order order);

    void saveOrderItem(Connection connection,OrderItem item);
}
