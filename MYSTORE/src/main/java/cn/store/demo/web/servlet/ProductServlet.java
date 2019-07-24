package cn.store.demo.web.servlet;

import cn.store.demo.domain.Product;
import cn.store.demo.service.ProductService;
import cn.store.demo.service.impl.ProductServiceImpl;
import cn.store.demo.web.base.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/ProductServlet")
public class ProductServlet extends BaseServlet {
    /**
     * 查询商品的详细信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String findProductById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取到pid,获取到该商品的详细信息
        String pid = request.getParameter("pid");
        ProductService productService = new ProductServiceImpl();
        Product p= productService.findProductById(pid);
        request.setAttribute("productInfo",p);
        return "/jsp/product_info.jsp";
    }
}
