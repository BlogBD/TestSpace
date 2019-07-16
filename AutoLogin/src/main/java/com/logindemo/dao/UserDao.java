package com.logindemo.dao;

import com.logindemo.bean.UserBean;

public interface UserDao {
    /**
     * 执行登陆，返回用户所有信息
     * @param user
     * @return
     */
    UserBean login(UserBean user);
}
