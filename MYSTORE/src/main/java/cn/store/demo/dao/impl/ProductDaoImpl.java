package cn.store.demo.dao.impl;

import cn.store.demo.dao.ProductDao;
import cn.store.demo.domain.Product;
import cn.store.demo.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 对商品表的操作
 */
public class ProductDaoImpl implements ProductDao {
    private static final QueryRunner qr;

    static {
         qr = new QueryRunner(JDBCUtils.getDataSource());
    }

    /**
     * 查询最活最新的商品
     * @return
     * @throws SQLException
     */
    @Override
    public List<Product> findHots() throws SQLException {
        String sql="select * from product where pflag= 0 and is_hot = 1 order by pdate desc limit 0 , 9 ";
        List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
        return list;
    }

    /**
     * 实现查询最新商品
     * @return
     * @throws SQLException
     */
    public List<Product> findNews() throws SQLException {
        String sql="select * from product where pflag=0 order by pdate desc limit 0 , 9";
        List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
        return list;
    }

    /**
     * 通过pid获取商品的详细信息
     * @param pid
     * @return
     * @throws SQLException
     */
    @Override
    public Product findProductById(String pid) throws SQLException {
        String sql="select * from product where pid = ?";
        Product p = qr.query(sql, new BeanHandler<>(Product.class), pid);
        return p;
    }
}
