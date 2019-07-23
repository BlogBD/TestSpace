package cn.store.demo.dao;

import cn.store.demo.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> getAll() throws SQLException;
}
