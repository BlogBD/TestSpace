package cn.store.demo.service.impl;

import cn.store.demo.dao.UserDao;
import cn.store.demo.dao.impl.UserDaoImpl;
import cn.store.demo.domain.User;
import cn.store.demo.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    /**
     * 用户注册业务实现
     * @param user
     * @throws SQLException
     */
    @Override
    public void userRegist(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        userDao.userRegist(user);
    }

    /**
     * 激活用户业务实现,并返回用户信息
     * @param code
     */
    @Override
    public User userActive(String code) throws SQLException {
        return new UserDaoImpl().userActive(code);
    }

    /**
     * 更新用户业务实现
     * @param user
     * @throws SQLException
     */
    @Override
    public void updateUser(User user) throws SQLException {
        new UserDaoImpl().updateUser(user);
    }

    /**
     * 实现登陆业务
     * @param user
     * @return
     */
    @Override
    public User userLogin(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        User user1 = userDao.userLogin(user.getUsername(), user.getPassword());
        return user1;
    }
}
