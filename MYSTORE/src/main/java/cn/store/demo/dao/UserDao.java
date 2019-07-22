package cn.store.demo.dao;

import cn.store.demo.domain.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * 定义注册规范
     * @param user
     * @throws SQLException
     */
    void userRegist(User user) throws SQLException;

    /**
     * 通过code查询用户是否激活
     * @param code 激活码
     * @return
     */
    User userActive(String code) throws SQLException;

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user) throws SQLException;
}


