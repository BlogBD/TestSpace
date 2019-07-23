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
}
