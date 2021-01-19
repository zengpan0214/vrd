package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entitiy.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Json1Servlet",urlPatterns = "/json1")
public class Json1Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器只能给客户端返回json字符串
        ProductDao dao=new ProductDao();
        List<Product> list=dao.findAll();
        //把集合转成json字符串  需要利用一个三方框架
        ObjectMapper om=new ObjectMapper();
        //这个方法可以捡任意对象转成json字符串
        String jsonStr=om.writeValueAsString(list);
        System.out.println(jsonStr);
        //把json字符串返回给客户端
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw=response.getWriter();
        pw.print(jsonStr);
        pw.close();
    }
}
