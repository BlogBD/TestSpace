package cn.store.demo.web.servlet;

import cn.store.demo.domain.Category;
import cn.store.demo.service.ProductService;
import cn.store.demo.service.impl.CategoryServiceImpl;
import cn.store.demo.service.impl.ProductServiceImpl;
import cn.store.demo.utils.PageModel;
import cn.store.demo.web.base.BaseServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/** 管理页的商品信息 */
@WebServlet(urlPatterns = "/AdminProductServlet")
public class AdminProductServlet extends BaseServlet {
  /**
   * 分页查询
   *
   * @param request
   * @param response
   * @return
   */
  public String findAllProductWithPage(HttpServletRequest request, HttpServletResponse response)
      throws SQLException {
    int num = Integer.parseInt(request.getParameter("num"));
    ProductService productService = new ProductServiceImpl();
    PageModel pm = productService.findProductsWithCidAndPage(num);
    request.setAttribute("page", pm);
    return "/admin/product/list.jsp";
  }

  /**
   * 跳转到添加商品页面
   *
   * @param request
   * @param response
   * @return
   * @throws SQLException
   */
  public String addProductUI(HttpServletRequest request, HttpServletResponse response)
      throws SQLException {
    List<Category> list = new CategoryServiceImpl().getAllCats();
    request.setAttribute("allCats", list);
    return "/admin/product/add.jsp";
  }

    /**
     * 实现添加
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
  public String addProduct(HttpServletRequest request, HttpServletResponse response)
      throws SQLException {
      try {
          //封装了request.getInputStream,
          // 获取到请求中的全部数据
          //进行拆分和封装
          DiskFileItemFactory factory = new DiskFileItemFactory();
          ServletFileUpload upload = new ServletFileUpload(factory);
          List<FileItem> list = upload.parseRequest(request);
          //遍历集合




          return "/admin/product/list.jsp";
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
  }
}
