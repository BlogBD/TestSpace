package cn.store.demo.domain;


public class OrderItem {

  private String itemid;
  private long quantity;
  private double total;
  //private String pid;
  private Product product;
 // private String oid;
  private Order order;


  public String getItemid() {
    return itemid;
  }

  public void setItemid(String itemid) {
    this.itemid = itemid;
  }


  public long getQuantity() {
    return quantity;
  }

  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }


  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }
}
