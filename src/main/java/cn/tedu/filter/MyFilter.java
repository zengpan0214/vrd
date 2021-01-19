package cn.tedu.filter;

import cn.tedu.entitiy.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*urlPatterns 设置处理路径  通过{} 方式设置多个路径*/
@WebFilter(filterName = "MyFilter",urlPatterns = {"/showsend","/showbanner"})
public class MyFilter implements Filter {
    /*对象销毁时执行的方法*/
    public void destroy() {
    }
    /*开始执行过滤时执行的方法*/
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //如果执行过滤器后 允许访问后面的资源则调用下面代码 不允许则不调用
//        chain.doFilter(req, resp);
        //过滤器中的请求和响应对象为Servlet里面请求响应对象的父类
        //使用强转 转回HttpServletXXX
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        //获取session对象
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //判断是否登录
        if (user==null){
            response.sendRedirect("/showlogin");
        }else {
            chain.doFilter(req,resp);
        }
    }
    /*对象创建时执行的方法*/
    public void init(FilterConfig config) throws ServletException {

    }

}
