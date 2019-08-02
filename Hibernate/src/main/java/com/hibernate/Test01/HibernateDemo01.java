package com.hibernate.Test01;

import com.hibernate.mapping.Customer;
import com.hibernate.utils.HibernateUtils;
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

  /** 查询 */
  @Test
  public void test02() {
    // 获取session
    Session session = HibernateUtils.openSession();
    Transaction transaction = session.beginTransaction();
    // 使用get
   /* Customer customer = session.get(Customer.class, 1L);
    System.err.println(customer);*/
    // 使用load
    Customer load = session.load(Customer.class, 4L);
    System.err.println(load);
    /**
     * get方法：
     * 1.采用的是立即加载，执行到这行代码的时候，就会马上发送sql语句去查询 load方法：
     * 2.返回的是真实对象
     * load方法：
     * 1.采用的是延迟加载（lazy加载），执行到load方法时不会发送sql语句，当使用这个对象时才发送sql语句
     * 2.返回一个代理对象，javassist-3.2.1-GA.jar 利用的javassist技术产生的代理对象
     */
    transaction.commit();
  }
}
