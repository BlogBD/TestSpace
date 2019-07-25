package cn.store.demo.service.impl;

import cn.store.demo.dao.ProductDao;
import cn.store.demo.dao.impl.ProductDaoImpl;
import cn.store.demo.domain.Product;
import cn.store.demo.service.ProductService;
import cn.store.demo.utils.PageModel;

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

    /**
     * 实现分页查询业务
     * @param cid
     * @param num
     * @return
     */
    @Override
    public PageModel findProductsWithCidAndPage(String cid, int num) throws SQLException {
        //获取总的条记录数
        int totalRecords=new ProductDaoImpl().findTotalRecords(cid);
        PageModel pm=new PageModel(num,totalRecords,12);
        ProductDao productDao = new ProductDaoImpl();
        List<Product>  list= productDao.findProductsWithCidAndPage(cid,pm.getStartIndex(),pm.getPageSize());
        pm.setList(list);//把查询到的信息封装到pagemodel中
    pm.setUrl("ProductServlet?method=findProductsWithCidAndPage&cid="+cid);
        return pm;
    }
}
