package cn.store.demo.web.servlet;

import cn.store.demo.domain.Category;
import cn.store.demo.service.CategoryService;
import cn.store.demo.service.impl.CategoryServiceImpl;
import cn.store.demo.web.base.BaseServlet;
import net.sf.json.JSONArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = "/CategoryServlet")
public class CategoryServlet extends BaseServlet {

  public String findAllCats(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // 获取全部的分类信息
    CategoryService categoryService = new CategoryServiceImpl();
    List<Category> list = categoryService.getAllCats();
    // 将全部数据转换成json格式的文件
    String s = JSONArray.fromObject(list).toString();
    // 将全部数据响应给客服端,告诉浏览器本次响应的是json格式的字符串
    response.setContentType("application/json;charset=utf-8");
    response.getWriter().print(s);
    return null;
  }
}
