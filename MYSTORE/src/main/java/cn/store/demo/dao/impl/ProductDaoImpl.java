package cn.store.demo.dao.impl;

import cn.store.demo.dao.ProductDao;
import cn.store.demo.domain.Product;
import cn.store.demo.utils.JDBCUtils;
import cn.store.demo.utils.PageModel;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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

    /**
     * 获取通过cid总的记录数
     * @param cid
     * @return
     */
    @Override
    public int findTotalRecords(String cid) throws SQLException {
        String sql="select count(*) from product where cid = ?";
        Long count = (Long)qr.query(sql, new ScalarHandler<>(), cid);
        return count.intValue();
    }

    /**
     * 通过分页算法，实现分页查询当前页的list集合
     * @param cid
     * @param startIndex
     * @param pageSize
     * @return
     * @throws SQLException
     */
    @Override
    public List<Product> findProductsWithCidAndPage(String cid, int startIndex, int pageSize) throws SQLException {
        String sql="select * from product where cid=? limit ?,?";
        List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class), cid,startIndex, pageSize);
        return list;
    }

    /**
     * 查询所有商品的总数
     * @return
     */
    public  int findTotalRecords() throws SQLException {
        String sql="select count(*) from product";
        Long count =(Long) qr.query(sql, new ScalarHandler<>());
        return count.intValue();
    }

    /**
     * 实现分页查询
     * @param startIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Product> findProductsWithCidAndPage(int startIndex, int pageSize) throws SQLException {
        String sql="select * from product  limit ?,?";
        List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex, pageSize);
        return list;
    }

    /**
     * 实现保存商品
     * @param product
     */
    @Override
    public void saveProduct(Product product) throws SQLException {
        String sql="INSERT INTO product VALUES( ?,?,?,?,?,?,?,?,?,? )";
        Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIsHot(),product.getPdesc(),product.getPflag(),product.getCid()};
        QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
        qr.update(sql,params);
    }
}
