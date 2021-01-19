package cn.tedu.controller;

import cn.tedu.dao.UserDao;
import cn.tedu.entitiy.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet",urlPatterns = "/loginaction")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取参数
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String rem=request.getParameter("rem");
        System.out.println(rem);
        //抄件UserDao 冰调用Login方法
        UserDao dao=new UserDao();
        //如果登录成功返回User对象 否则返回null
        User user=dao.login(username,password);
        if (user!=null){
            if(rem!=null) {//需要记住用户名和密码
                //创建cookie把用户名和密码保存到cookie中
                Cookie c1=new Cookie("username",username);
                Cookie c2=new Cookie("password",password);
                c1.setMaxAge(60*60*24*30);
                //把cookie下发到浏览器
                response.addCookie(c1);
                response.addCookie(c2);
            }
            //记住登录状态
            HttpSession session=request.getSession();
            //把当前登录的用户对象保存到session里面
            session.setAttribute("user",user);

            System.out.println("登录成功");
            //重定向到首页列表页面
            response.sendRedirect("/home");
        }else {
            System.out.println("登录失败");
            //重定向到登录的页面
            response.sendRedirect("/showlogin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
