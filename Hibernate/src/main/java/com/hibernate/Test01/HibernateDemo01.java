package com.hibernate.Test01;

import com.hibernate.mapping.Customer;
import com.hibernate.utils.HibernateUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
    session.close();
  }

  /**
   * 修改操作
   */
  @Test
  public void test03(){
    Session session = HibernateUtils.openSession();
    Transaction transaction = session.beginTransaction();
    //1.先直接创建对象，进行修改
    Customer customer = new Customer();
    customer.setCustName("王小二");
    customer.setCustId(5L);
    session.update(customer);
    //2.先修改，在修改（推荐）
    Customer customer1 = session.get(Customer.class, 5L);
    customer1.setCustName("王小贱");
    session.update(customer);
    transaction.commit();
    session.close();
  }

  /**
   * 删除
   */
  @Test
  public void test04(){
    Session session = HibernateUtils.openSession();
    Transaction transaction = session.beginTransaction();
    //直接创建对象删除
   /* Customer customer = new Customer();
    customer.setCustId(3L);
    session.delete(customer);*/
    //先查询在删除(推荐)
    Customer customer1 = session.get(Customer.class, 3L);
    session.delete(customer1);
    transaction.commit();
    session.close();
  }

  /**
   * 查询所有
   */
  @Test
  public void test05(){
    Session session = HibernateUtils.openSession();
    Transaction transaction = session.beginTransaction();
    //HQL:Hibernate Query Language 面向对象的查询语言
    Query from_customer_ = session.createQuery("from Customer ");
    List<Customer> list = from_customer_.list();
    list.forEach(i -> System.out.println(i));
    //list.forEach(System.out::println);

    //sql
    SQLQuery sqlQuery = session.createSQLQuery("select * from cst_customer");
    List<Object[]> list1 = sqlQuery.list();
    list1.forEach(i -> System.out.println(Arrays.toString(i)));
    transaction.commit();
    session.close();
  }
}
