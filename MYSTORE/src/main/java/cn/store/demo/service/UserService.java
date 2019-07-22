package cn.store.demo.service;

import cn.store.demo.domain.User;

import java.sql.SQLException;

public interface UserService {
    /**
     * 用户的注册业务规范
     * @param user
     * @throws SQLException
     */
    public void userRegist(User user) throws SQLException;

    /**
     * 用户激活账户业务规范
     * @param code
     */
    public User userActive(String code) throws SQLException;

    /**
     * 用户更新业务规范
     * @param user
     * @throws SQLException
     */
    void updateUser(User user) throws SQLException;

    /**
     * 登陆业务规范
     * @param user
     */
    User userLogin(User user) throws SQLException;
}
