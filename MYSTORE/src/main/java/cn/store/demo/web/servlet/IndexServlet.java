package cn.store.demo.web.servlet;

import cn.store.demo.domain.Category;
import cn.store.demo.service.CategoryService;
import cn.store.demo.service.impl.CategoryServiceImpl;
import cn.store.demo.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/IndexServlet")
public class IndexServlet extends BaseServlet {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            //获取全部的分类信息
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> list= categoryService.getAll();
            //将返回的集合放入request中
            req.setAttribute("allCats",list);
            //转发到jsp中的首页index.jsp
            return "/jsp/index.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
            return "/jsp/index.jsp";
        }

    }
}
