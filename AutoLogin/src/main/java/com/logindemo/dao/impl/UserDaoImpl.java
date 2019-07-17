package com.logindemo.dao.impl;

import com.logindemo.bean.UserBean;
import com.logindemo.dao.UserDao;
import com.logindemo.util.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    /**
     * 实现登陆查询
     * @param user
     * @return
     */
    @Override
    public UserBean login(UserBean user) {
        UserBean has=null;
        QueryRunner runner = new QueryRunner(JDBCUtil.getDataSource());
        try {
           has= runner.query("select * from t_user where username=? and password=?",
                   new BeanHandler< UserBean >(UserBean.class), user.getUsername(),user.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return has;
    }
}
