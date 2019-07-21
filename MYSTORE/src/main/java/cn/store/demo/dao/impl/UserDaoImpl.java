package cn.store.demo.dao.impl;

import cn.store.demo.dao.UserDao;
import cn.store.demo.domain.User;
import cn.store.demo.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
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
}
