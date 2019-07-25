package cn.store.demo.web.servlet;

import cn.store.demo.domain.Product;
import cn.store.demo.service.ProductService;
import cn.store.demo.service.impl.ProductServiceImpl;
import cn.store.demo.utils.PageModel;
import cn.store.demo.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 商品的查询 */
@WebServlet(urlPatterns = "/ProductServlet")
public class ProductServlet extends BaseServlet {
  /**
   * 查询商品的详细信息
   *
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public String findProductById(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // 获取到pid,获取到该商品的详细信息
    String pid = request.getParameter("pid");
    ProductService productService = new ProductServiceImpl();
    Product p = productService.findProductById(pid);
    request.setAttribute("productInfo", p);
    return "/jsp/product_info.jsp";
  }

  /**
   * 分页分类商品查询
   *
   * @param request
   * @param response
   * @return
   * @throws Exception
   */
  public String findProductsWithCidAndPage(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String cid = request.getParameter("cid"); // 获取cid
    int num = Integer.parseInt(request.getParameter("num")); // 获取当前页
    ProductService productService = new ProductServiceImpl();
    PageModel pageModel = productService.findProductsWithCidAndPage(cid, num);
    request.setAttribute("page", pageModel); // 把查询到的放入request域中
    return "/jsp/product_list.jsp";
  }
}
