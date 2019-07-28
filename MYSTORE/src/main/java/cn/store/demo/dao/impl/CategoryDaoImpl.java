package cn.store.demo.dao.impl;

import cn.store.demo.dao.CategoryDao;
import cn.store.demo.domain.Category;
import cn.store.demo.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> getAll() throws SQLException {
        String spl="select * from category";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        List<Category> list = qr.query(spl, new BeanListHandler<Category>(Category.class));
        return list;
    }

    /**
     * 实现分类信息的添加
     * @param category
     */
    @Override
    public void addCategory(Category category) {
        try {
            String spl="insert into  category values (?,?)";
            QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
            qr.update(spl,category.getCid(),category.getCname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
