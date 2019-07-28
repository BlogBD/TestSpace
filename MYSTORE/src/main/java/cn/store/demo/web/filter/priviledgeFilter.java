package cn.store.demo.web.filter;

import cn.store.demo.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "priviledgeFilter" ,urlPatterns = {"/jsp/cart.jsp","/jsp/order_info.jsp","/jsp/order_list.jsp"})
public class priviledgeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 1.强转
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //判断session中是否有已经登陆的用户
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("loginUser");
        if (user!=null){
            //说明有用户登陆
            chain.doFilter(req, resp);
        }
        //没有用户登陆，跳转到提示页面
        request.setAttribute("msg","请登陆!");
        request.getRequestDispatcher("/jsp/info.jsp").forward(request,response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
