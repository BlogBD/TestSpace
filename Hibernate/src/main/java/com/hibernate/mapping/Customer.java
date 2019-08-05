package com.hibernate.mapping;
/*
create table cst_customer
(
    cust_id       bigint(32) auto_increment comment '客户编号(主键)'
        primary key,
    cust_name     varchar(32) not null comment '客户名称(公司名称)',
    cust_source   varchar(32) null comment '客户信息来源',
    cust_industry varchar(32) null comment '客户所属行业',
    cust_level    varchar(32) null comment '客户级别',
    cust_phone    varchar(64) null comment '固定电话',
    cust_mobile   varchar(16) null comment '移动电话'
);

*/

import java.util.Set;

/**
 * 客服管理实体类
 */
public class Customer {
    private long custId;
    private String custName;
    private String custSource;
    private String custIndustry;
    private String custLevel;
    private String custPhone;
    private String custMobile;
    private Set<CstLinkman> cstLinkmanSet;

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }


    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }


    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }


    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }


    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }


    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }


    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {

        this.custMobile = custMobile;
    }

    public Set<CstLinkman> getCstLinkmanSet() {
        return cstLinkmanSet;
    }

    public void setCstLinkmanSet(Set<CstLinkman> cstLinkmanSet) {
        this.cstLinkmanSet = cstLinkmanSet;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custMobile='" + custMobile + '\'' +
                '}';
    }
}
