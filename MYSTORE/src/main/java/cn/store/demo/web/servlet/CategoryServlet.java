package cn.store.demo.web.servlet;

import cn.store.demo.domain.Category;
import cn.store.demo.service.CategoryService;
import cn.store.demo.service.impl.CategoryServiceImpl;
import cn.store.demo.utils.JedisUtils;
import cn.store.demo.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 查询全部分类信息
 */
@WebServlet(urlPatterns = "/CategoryServlet")
public class CategoryServlet extends BaseServlet {

  public String findAllCats(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    // 从redis中获取分类信息
    Jedis jedis = JedisUtils.getJedis();
    String allCats = jedis.get("allCats");
    if (allCats == null || "".equals(allCats)) { // 如果在redis中不存在分类信息，执行下面
      // 获取全部的分类信息
      CategoryService categoryService = new CategoryServiceImpl();
      List<Category> list = categoryService.getAllCats();
      // 将全部数据转换成json格式的文件
      allCats = JSONArray.fromObject(list).toString();
      // 把查询到的分类信息放入redis中
      jedis.set("allCats", allCats);
    }
    // 将全部数据响应给客服端,告诉浏览器本次响应的是json格式的字符串
    response.setContentType("application/json;charset=utf-8");
    response.getWriter().print(allCats);
    JedisUtils.closeJedis(jedis);
    return null;
  }
}
