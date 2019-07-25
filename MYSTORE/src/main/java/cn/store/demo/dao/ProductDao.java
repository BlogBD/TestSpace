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

    /**
     * 通过cid查询到该类的总的记录数
     * @param cid
     * @return
     */
    int findTotalRecords(String cid) throws SQLException;

    /**
     * 通过分页信息查询到该页的商品集合
     * @param cid
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<Product> findProductsWithCidAndPage(String cid, int startIndex, int pageSize) throws SQLException;

}
