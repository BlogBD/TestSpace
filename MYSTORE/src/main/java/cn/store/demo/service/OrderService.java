package cn.store.demo.service;

import cn.store.demo.domain.Order;
import cn.store.demo.domain.User;
import cn.store.demo.utils.PageModel;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface OrderService {
    /**
     * 保存订单到数据库
     * @param order
     */
    void saveOrder(Order order) throws Exception;

    /**
     * 分页查询订单
     * @param user
     * @param num
     * @return
     */
    PageModel findMyOrderWithPage(User user, int num) throws SQLException, InvocationTargetException, IllegalAccessException;
}
