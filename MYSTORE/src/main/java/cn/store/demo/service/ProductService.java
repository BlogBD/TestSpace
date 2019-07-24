package cn.store.demo.service;

import cn.store.demo.domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品表的业务规范
 */
public interface ProductService {
    /**
     * 规范查询最热，最新商品业务
     * @return
     * @throws SQLException
     */
    List<Product> findHots() throws SQLException;

    /**
     * 规范查询最新商品业务
     * @return
     * @throws SQLException
     */
    List<Product> findNews() throws SQLException;
}
