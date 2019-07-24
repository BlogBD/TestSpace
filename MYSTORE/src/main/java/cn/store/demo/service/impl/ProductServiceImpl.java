package cn.store.demo.service.impl;

import cn.store.demo.dao.impl.ProductDaoImpl;
import cn.store.demo.domain.Product;
import cn.store.demo.service.ProductService;

import java.sql.SQLException;
import java.util.List;

/**
 * 商品业务层的实现
 */
public class ProductServiceImpl implements ProductService {
    /**
     * 实现查询到最热，最新的商品业务
     * @return
     */
    @Override
    public List<Product> findHots() throws SQLException {
        return new ProductDaoImpl().findHots();
    }

    /**
     * 实现查询最新的商品业务
     * @return
     * @throws SQLException
     */
    @Override
    public List<Product> findNews() throws SQLException {
        return new ProductDaoImpl().findNews();
    }

    /**
     * 实现通pid查询到该商品详情的业务
     * @param pid
     * @return
     */
    @Override
    public Product findProductById(String pid) throws SQLException {
        return new ProductDaoImpl().findProductById(pid);
    }
}
