package cn.store.demo.dao;

import cn.store.demo.domain.User;

import java.sql.SQLException;

public interface UserDao {
    void userRegist(User user) throws SQLException;
}
