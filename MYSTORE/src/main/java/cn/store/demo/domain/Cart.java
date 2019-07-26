package cn.store.demo.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/** 购物车的封装 */
public class Cart {
  private double total = 0; // 总的购物价格
  private Map<String, CartItem> map = new HashMap<>(); // 购物列表

  // 添加购物项到购物车
  public void addCartItemToCart(CartItem cartItem) {
    String pid = cartItem.getProduct().getPid(); // 获取添加商品的pid
    if (map.containsKey(pid)) {
      // map的key中包含这个pid说明添加过
      int num = map.get(pid).getNum() + cartItem.getNum(); // 该购物项现在的购买数量
      map.get(pid).setNum(num);
    } else {
      // 购物车中无该购物项
      map.put(pid, cartItem);
    }
  }
  // 移除购物项
  public void removeCartItem(String pid) {
    map.remove(pid);
  }
  // 清空购物车
  public void clearCart() {
    map.clear();
  }

    /**
     * 在cart.jsp用于判断map中是否为空，和遍历购物项
     * @return
     */
  public Collection<CartItem> getCartItems() {
    return map.values();
  }
  // 获取总计
  public double getTotal() {
    total=0;
    Collection<CartItem> values = map.values(); // 获取所有购物项
    for (CartItem cartItem : values) {
      total += cartItem.getSubTotal();
    }
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public Map<String, CartItem> getMap() {
    return map;
  }

  public void setMap(Map<String, CartItem> map) {
    this.map = map;
  }
}
