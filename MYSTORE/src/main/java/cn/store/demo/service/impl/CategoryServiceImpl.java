package cn.store.demo.service.impl;

import cn.store.demo.dao.CategoryDao;
import cn.store.demo.dao.impl.CategoryDaoImpl;
import cn.store.demo.domain.Category;
import cn.store.demo.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> getAll() throws SQLException {
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> list=categoryDao.getAll();
        return list;
    }
}
