package cn.store.demo.dao;

import cn.store.demo.domain.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * 最商品表的查询
 */
public interface ProductDao {
    /**
     * 查询最畅销最新的商品
     * @return
     */
    List<Product> findHots() throws SQLException;

    /**
     * 查询最新的商品
     * @return
     */
     List<Product> findNews() throws SQLException;

    /**
     * 通过pid查询该商品的详细信息
     * @param pid
     * @return
     */
    Product findProductById(String pid) throws SQLException;
}
