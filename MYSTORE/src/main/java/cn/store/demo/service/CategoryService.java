package cn.store.demo.service;

import cn.store.demo.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    /***
     * 获取分类信息
     * @return
     * @throws SQLException
     */
    List<Category> getAllCats() throws SQLException;

    /**
     * 获取分类信息
     * @return
     * @throws SQLException
     */
    List<Category> findAllCats() throws SQLException;

    /**
     * 实现添加分类信息
     */
    void addCategory(Category category);
}
