package cn.store.demo.service;

import cn.store.demo.domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    List<Category> getAll() throws SQLException;
}
