package com.hibernate.Test01;

import com.hibernate.mapping.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/** Hibernate测试 */
public class HibernateDemo01 {
  /** 保存客服信息 */
  @Test
  public void test01() {
    // 1.加载hibernate核心配置文件
    Configuration configure = new Configuration().configure();
    // 2.创建一个sessionFactory对象：类似与连接池
    SessionFactory sessionFactory = configure.buildSessionFactory();
    // 3.通过sessionFactory获取Session对象
    Session session = sessionFactory.openSession();
    // 4.手动开启事务
    Transaction transaction = session.beginTransaction();
    // 5.业务实现
    Customer customer = new Customer();
    customer.setCustName("王五");
    session.save(customer);
    // 6.提交事务
    transaction.commit();
    // 7.资源释放
    session.close();
  }
}
