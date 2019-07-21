package cn.store.demo.service;

import cn.store.demo.domain.User;

import java.sql.SQLException;

public interface UserService {
    public void userRegist(User user) throws SQLException;
}
