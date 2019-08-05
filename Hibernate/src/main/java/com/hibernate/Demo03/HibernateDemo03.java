package com.hibernate.Demo03;

import com.hibernate.mapping.CstLinkman;
import com.hibernate.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

/** 一对多关联映射 多对多关联映射 */
public class HibernateDemo03 {
  /** 测试表连接正常 */
  @Test
  public void test01() {
    Session session = HibernateUtils.getCurrentSession();
    Transaction transaction = session.beginTransaction();
    Query query = session.createQuery("from CstLinkman ");
    query.list().forEach(i -> System.out.println((CstLinkman) i));
    transaction.commit();
  }
}
