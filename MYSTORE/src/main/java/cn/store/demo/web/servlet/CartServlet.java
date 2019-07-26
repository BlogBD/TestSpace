package cn.store.demo.web.servlet;

import cn.store.demo.domain.Cart;
import cn.store.demo.domain.CartItem;
import cn.store.demo.domain.Product;
import cn.store.demo.service.ProductService;
import cn.store.demo.service.impl.ProductServiceImpl;
import cn.store.demo.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/CartServlet")
public class CartServlet extends BaseServlet {
  /**
   * 添加购物项到购物车
   *
   * @param request
   * @param response
   * @return
   */
  public String addCartItemToCart(HttpServletRequest request, HttpServletResponse response)
      throws SQLException {
    Cart cart = null; // 存储购物车
    // 从session中的购物车
    cart = (Cart) request.getSession().getAttribute("cart");
    // 第一次访问，没有购物车
    if (cart == null) {
      cart = new Cart(); // 创建一个购物车
      request.getSession().setAttribute("cart", cart); // 放入session中
    }
    // 获取到了购物车对象
    String pid = request.getParameter("pid"); // 商品pid
    int quantity = Integer.parseInt(request.getParameter("quantity")); // 添加商品的数量
    // 通过商品pid创建商品的信息
    ProductService productService = new ProductServiceImpl();
    Product product = productService.findProductById(pid);
    // 创建购物项
    CartItem cartItem = new CartItem();
    cartItem.setProduct(product);
    cartItem.setNum(quantity);
    // 添加购物项到购物车
    cart.addCartItemToCart(cartItem);
    return "/jsp/cart.jsp";
  }

  /**
   * 删除一个购物项
   *
   * @param request
   * @param response
   * @return
   */
  public String deleteCartItemById(HttpServletRequest request, HttpServletResponse response) {
    String pid = request.getParameter("pid");
    Cart cart = null; // 存储购物车
    // 从session中的购物车
    cart = (Cart) request.getSession().getAttribute("cart");
    // 删除购物车项目
    cart.removeCartItem(pid);
    return "/jsp/cart.jsp";
  }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @return
     */
  public String clearCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // 获取购物车
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    // 清空购物车
    cart.clearCart();
    response.sendRedirect("/MYSTORE/jsp/cart.jsp");
    return null;
  }
}
