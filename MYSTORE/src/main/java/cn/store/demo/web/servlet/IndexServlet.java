package cn.store.demo.web.servlet;

import cn.store.demo.domain.Product;
import cn.store.demo.service.ProductService;
import cn.store.demo.service.impl.ProductServiceImpl;
import cn.store.demo.web.base.BaseServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * 跳转到首页
 */
@WebServlet(urlPatterns = "/IndexServlet")
public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //调用业务层查询到最新，最热的商品，返回两个人集合
        ProductService productService = new ProductServiceImpl();
       List<Product> hots=productService.findHots();
       List<Product> news=productService.findNews();
       //放入到请求中
       request.setAttribute("hots",hots);
       request.setAttribute("news",news);
        //将集合放入request中
        return "/jsp/index.jsp";
    }
}
