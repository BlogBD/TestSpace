package cn.store.demo.service;

import cn.store.demo.domain.Product;
import cn.store.demo.utils.PageModel;

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

    /**
     * 通过pid查询到该商品的所有信息详情
     * @param pid
     * @return
     */
    Product findProductById(String pid) throws SQLException;

    /**
     * 查询分页信息
     * @param cid
     * @param num
     * @return
     */
    PageModel findProductsWithCidAndPage(String cid, int num) throws SQLException;
}
