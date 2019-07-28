package cn.store.demo.dao;

import cn.store.demo.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    /**
     * 获取全部分类信息
     * @return
     * @throws SQLException
     */
    List<Category> getAll() throws SQLException;

    /**
     * 添加分类信息
     * @param category
     */
    void addCategory(Category category);
}
