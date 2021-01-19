package cn.tedu.controller;

import cn.tedu.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LikeServlet",urlPatterns = "/like")
public class LikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        HttpSession session=request.getSession();
        //通过id获取session里面是否有对应的数据
        String likeId = (String)session.getAttribute("like" + id);
        if(likeId==null) {
            //创建dao
            ProductDao dao = new ProductDao();
            dao.likeById(id);
            //把修改之后的id添加到session对象中
            session.setAttribute("like" + id, id);
        }
            response.sendRedirect("/detail?id="+id);
    }
}
