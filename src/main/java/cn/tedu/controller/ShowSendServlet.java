package cn.tedu.controller;

import cn.tedu.dao.CategoryDao;
import cn.tedu.entitiy.Category;
import cn.tedu.entitiy.User;
import cn.tedu.utils.ThUtils;
import org.thymeleaf.context.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowSendServlet",urlPatterns = "/showsend")
public class ShowSendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 判断是否登录
        HttpSession session=request.getSession();
        User user = (User) session.getAttribute("user");
        if (user==null){
            response.sendRedirect("/showlogin");
            return;
        }
        Context context=new Context();
        CategoryDao dao=new CategoryDao();
        List<Category> list=dao.findAll();
        context.setVariable("list",list);

        ThUtils.print("send.html",context,response);
    }
}
