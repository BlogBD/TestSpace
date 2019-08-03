package com.hibernate.Demo02;

import com.hibernate.Demo01.mapping.Customer;
import com.hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 演示主键生成策略
 */
public class HibernateDemo02 {
    @Test
   public void test01(){
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        //保存一条记录
        Customer customer = new Customer();
        customer.setCustName("小白");
        session.save(transaction);
        transaction.commit();
        session.close();
    }
}
