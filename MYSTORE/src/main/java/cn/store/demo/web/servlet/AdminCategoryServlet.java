package cn.store.demo.web.servlet;

import cn.store.demo.domain.Category;
import cn.store.demo.service.CategoryService;
import cn.store.demo.service.impl.CategoryServiceImpl;
import cn.store.demo.utils.UUIDUtils;
import cn.store.demo.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet(urlPatterns = "/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
    /**
     * 查询到所有的分类信息
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //获取全部分类信息
        CategoryService categoryService = new CategoryServiceImpl();
       List<Category> list= categoryService.findAllCats();
       //将获取到的分类放入request中
       request.setAttribute("allCats" ,list);
        return "/admin/category/list.jsp";
    }

    /**
     * 跳转到添加到商品分类信息
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
  public String addCategoryUI(HttpServletRequest request, HttpServletResponse response)
      throws SQLException {
      return "/admin/category/add.jsp";
  }

    /**
     * 实现分类的添加
     * @param request
     * @param response
     * @return
     * @throws SQLException
     */
    public String addCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String cname = request.getParameter("cname");
        Category category = new Category();
        //封装了要添加的分类信息
        category.setCid(UUIDUtils.getId());
        category.setCname(cname);
        //调用业务层实现添加分类信息
        CategoryService categoryService = new CategoryServiceImpl();
        categoryService.addCategory(category);
        response.sendRedirect(request.getContextPath()+"/AdminCategoryServlet?method=findAllCats");
        return null;
}
}
