package com.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    public static final Configuration cfg;
    public static final SessionFactory sf;

    static{
        //加载配置文件 默认名字为public static final String DEFAULT_CFG_RESOURCE_NAME = "hibernate.cfg.xml";
        cfg = new Configuration().configure();
        sf = cfg.buildSessionFactory();
    }

    public static Session openSession(){
        return sf.openSession();
    }
}
