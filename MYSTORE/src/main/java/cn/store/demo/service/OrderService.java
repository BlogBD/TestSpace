package cn.store.demo.service;

import cn.store.demo.domain.Order;

import java.sql.SQLException;

public interface OrderService {
    /**
     * 保存订单到数据库
     * @param order
     */
    void saveOrder(Order order) throws Exception;
}
