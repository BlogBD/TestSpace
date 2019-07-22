package cn.store.demo.dao.impl;

import cn.store.demo.dao.UserDao;
import cn.store.demo.domain.User;
import cn.store.demo.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    /**
     * 实现注册用户
     * @Override
     * @param user
     * @throws SQLException
     */
    public void userRegist(User user) throws SQLException {
        String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        Object [] params={ user.getUid(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getEmail(),
                user.getTelephone(),
                user.getBirthday(),
                user.getSex(),
                user.getState(),
                user.getCode()};
        qr.update(sql,params);
    }

    /**
     * 实现激活用户
     * @param code 激活码
     * @return
     */
    @Override
    public User userActive(String code) throws SQLException {
        String sql="select * from  user where code = ?" ;
        QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
        User u = qr.query(sql, new BeanHandler<>(User.class), code);
        return u;
    }

    /**
     * 实现用户的更新
     * @param user
     * @throws SQLException
     */
    @Override
    public void updateUser(User user) throws SQLException {
        String sql="UPDATE user SET username= ? ,password=? ,name =? ,email =? ,telephone =? , birthday =?  ,sex =? ,state= ? , CODE = ? WHERE uid=?";
        Object[] params={user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode(),user.getUid()};
        new QueryRunner(JDBCUtils.getDataSource()).update(sql,params);
    }

    /**
     * 实现用户登陆，并返回用户信息
     * @param username
     * @param password
     * @return
     */
    @Override
    public User userLogin(String username, String password) throws SQLException {
        String sql="select * from user where username = ? and password =?";
        User user = new QueryRunner(JDBCUtils.getDataSource()).query(sql, new BeanHandler<>(User.class), username, password);
        return user;
    }
}
