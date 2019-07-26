package cn.store.demo.dao.impl;

import cn.store.demo.dao.OrderDao;
import cn.store.demo.domain.Order;
import cn.store.demo.domain.OrderItem;

import java.sql.Connection;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void saveOrder(Connection connection,Order order) {

    }

    @Override
    public void saveOrderItem(Connection connection,OrderItem item) {

    }
}
