package cn.store.demo.domain;


import java.util.ArrayList;
import java.util.List;

public class Order {

  private String oid;
  private java.sql.Date ordertime;
  private double total;
  private long state;
  private String address;
  private String name;
  private String telephone;
 // private String uid;
  private User user;
  private List<Orderitem> list=new ArrayList<>();


  public String getOid() {
    return oid;
  }

  public void setOid(String oid) {
    this.oid = oid;
  }


  public java.sql.Date getOrdertime() {
    return ordertime;
  }

  public void setOrdertime(java.sql.Date ordertime) {
    this.ordertime = ordertime;
  }


  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }


  public long getState() {
    return state;
  }

  public void setState(long state) {
    this.state = state;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

    public List<Orderitem> getList() {
        return list;
    }

    public void setList(List<Orderitem> list) {
        this.list = list;
    }
}
