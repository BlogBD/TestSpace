package com.logindemo.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 通过c3p0连接池获取一个Connection连接
 */
public class JDBCUtil {
  private static ComboPooledDataSource dataSource = null;

  static {
    //会自动取寻找c3p0-config.xml
    dataSource = new ComboPooledDataSource();
  }

  public static DataSource getDataSource() {
    return dataSource;
  }

  /**
   * 关闭资源
   */
  public static void close(){
    dataSource.close();
  }

  /**
   * 获得数据库连接
   * @return 返回 Connection
   */
  public static Connection getConnection() {
    Connection conn = null;
    try {
      conn = dataSource.getConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }
  /**
   * 释放资源
   *
   * @param conn
   * @param st
   * @param rs
   */
  public static void release(Connection conn, Statement st, ResultSet rs) {
    closeRs(rs);
    closeSt(st);
    closeConn(conn);
  }

  public static void release(Connection conn, Statement st) {
    closeSt(st);
    closeConn(conn);
  }

  private static void closeRs(ResultSet rs) {
    try {
      if (rs != null) {
        rs.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      rs = null;
    }
  }

  private static void closeSt(Statement st) {
    try {
      if (st != null) {
        st.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      st = null;
    }
  }

  private static void closeConn(Connection conn) {
    try {
      if (conn != null) {
        conn.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      conn = null;
    }
  }

/*  public static void main(String[] args) {
    // 测试d
    Connection connection = JDBCUtil.getConnection();
    if (connection != null) {
      System.out.println("连接成功");
    } else {
      System.out.println("连接失败");
    }
  }*/
}
