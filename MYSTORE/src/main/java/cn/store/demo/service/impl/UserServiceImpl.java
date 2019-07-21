package cn.store.demo.service.impl;

import cn.store.demo.dao.UserDao;
import cn.store.demo.dao.impl.UserDaoImpl;
import cn.store.demo.domain.User;
import cn.store.demo.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public void userRegist(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        userDao.userRegist(user);
    }
}
