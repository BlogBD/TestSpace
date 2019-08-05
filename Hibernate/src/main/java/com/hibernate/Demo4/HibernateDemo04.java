package com.hibernate.Demo4;

import com.hibernate.mapping.CstLinkman;
import com.hibernate.mapping.Customer;
import com.hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

/** 一对多关联映射 多对多关联映射 */
public class HibernateDemo04 {
  /* 保存2个客服，3个联系人，并且建立好关系 */
  @Test
  public void test01() {
    Session session = HibernateUtils.getCurrentSession();
    Transaction transaction = session.beginTransaction();
    //客服
    Customer customer01 = new Customer();
    customer01.setCustName("曾敏");
    Customer customer02 = new Customer();
    customer02.setCustName("晓东");

    //联系人
    CstLinkman cstLinkman01 = new CstLinkman();
    cstLinkman01.setLkmName("one");
    CstLinkman cstLinkman02 = new CstLinkman();
    cstLinkman02.setLkmName("two");
    CstLinkman cstLinkman03 = new CstLinkman();
    cstLinkman03.setLkmName("three");
    //设置关系
    cstLinkman01.setCustomer(customer01);
    cstLinkman02.setCustomer(customer01);
    cstLinkman03.setCustomer(customer02);
    //保存关系
    session.save(cstLinkman01);
    session.save(cstLinkman02);
    session.save(cstLinkman03);
    session.save(customer01);
    session.save(customer02);
    transaction.commit();
  }
}
