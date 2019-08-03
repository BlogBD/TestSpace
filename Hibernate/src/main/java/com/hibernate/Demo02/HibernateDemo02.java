package com.hibernate.Demo02;

import com.hibernate.Demo01.mapping.Customer;
import com.hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.List;

/** 演示主键生成策略 */
public class HibernateDemo02 {
  @Test
  public void test01() {
    Session session = HibernateUtils.openSession();
    Transaction transaction = session.beginTransaction();
    // 保存一条记录
    Customer customer = new Customer();
    customer.setCustName("小白");
    session.save(transaction);
    transaction.commit();
    session.close();
  }

  /** hibernate其他api */
  @Test
  public void test02() {
    Session currentSession = HibernateUtils.getCurrentSession();
    Transaction transaction = currentSession.beginTransaction();
    // 通过Session获得Query
    Query query = currentSession.createQuery("from Customer where custName like ?");
    query.setParameter(0, "王%");
    List<Customer> list = query.list();
    list.forEach(System.out::println);
    transaction.commit();
  }

    /**
     * 分页查询
     */
}
