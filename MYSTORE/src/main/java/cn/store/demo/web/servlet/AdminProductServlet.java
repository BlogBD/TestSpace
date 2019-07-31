package cn.store.demo.web.servlet;

import cn.store.demo.domain.Category;
import cn.store.demo.domain.Product;
import cn.store.demo.service.ProductService;
import cn.store.demo.service.impl.CategoryServiceImpl;
import cn.store.demo.service.impl.ProductServiceImpl;
import cn.store.demo.utils.PageModel;
import cn.store.demo.utils.UUIDUtils;
import cn.store.demo.utils.UploadUtils;
import cn.store.demo.web.base.BaseServlet;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
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
   *
   * @param request
   * @param response
   * @return
   * @throws SQLException
   */
  public String addProduct(HttpServletRequest request, HttpServletResponse response)
      throws SQLException {
    try {
      // 封装了request.getInputStream,
      // 获取到请求中的全部数据
      // 进行拆分和封装
      DiskFileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload upload = new ServletFileUpload(factory);
      List<FileItem> list = upload.parseRequest(request);
      // 存储传上来的数据
      HashMap<String, String> map = new HashMap<>();
      // 封装商品信息
      Product product = new Product();
      // 遍历集合
      for (FileItem fileItem : list) {
        if (fileItem.isFormField()) {
          // 说明这是普通项
          map.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
        } else { // 上传项执行
          // 获取要保存的文件的名称,并通过工具类重新生成名字
          String oldFileName = fileItem.getName();
          String newFileName = UploadUtils.getUUIDName(oldFileName);
          InputStream inputStream = fileItem.getInputStream(); // 获取输入流
          // 创建一个空文件
          String realPath =
              getServletContext().getRealPath("/products/3"); // 获取到当前工程下的/products/3的绝对路径
          String dir = UploadUtils.getDir(newFileName);
          String path = realPath + dir;
          File filePath = new File(path);
          if (!filePath.exists()) {
            filePath.mkdirs(); // 创建多级目录
          }
          File file1 = new File(filePath, newFileName);
          if (!file1.exists()) {
            file1.createNewFile();
          }
          FileOutputStream fileOutputStream = new FileOutputStream(file1); // 创建输出流到file文件中
          IOUtils.copy(inputStream, fileOutputStream);
          IOUtils.closeQuietly(inputStream);
          IOUtils.closeQuietly(fileOutputStream);
          product.setPimage("/products/3"+dir+"/"+newFileName);//把图片路径保存到product对象中
          BeanUtils.populate(product,map);
          product.setPid(UUIDUtils.getId());
          product.setPdate(new Date(new java.util.Date().getTime()));
          product.setPflag(0);
          //调用业务层把数据保存到数据库
          ProductService productService = new ProductServiceImpl();
          productService.saveProduct(product);
        }
      }
      response.sendRedirect(request.getContextPath()+"/admin/product/list.jsp");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
