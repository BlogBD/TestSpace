package cn.store.demo.service.impl;

import cn.store.demo.dao.CategoryDao;
import cn.store.demo.dao.impl.CategoryDaoImpl;
import cn.store.demo.domain.Category;
import cn.store.demo.service.CategoryService;
import cn.store.demo.utils.JDBCUtils;
import cn.store.demo.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    /**
     * 获取全部分类信息
     * @return
     * @throws SQLException
     */
    @Override
    public List<Category> getAllCats() throws SQLException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> list=categoryDao.getAll();
        return list;
    }

    @Override
    public List<Category> findAllCats() throws SQLException {
        return getAllCats();
    }

    /**
     * 添加分类信息
     */
    @Override
    public void addCategory(Category category) {
       new  CategoryDaoImpl().addCategory(category);
        /**
         * 数据库更新了分类信息，但是redis中未更新，删除掉以前的redis中存储的allCats，
         * 首页时，会自动把更新的全部分类信息添加到redis中
         */
        Jedis jedis = JedisUtils.getJedis();
        jedis.del("allCats");//删除redis中的全部分类信息
        JedisUtils.closeJedis(jedis);
    }
}
