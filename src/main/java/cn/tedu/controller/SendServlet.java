package cn.tedu.controller;

import cn.tedu.dao.ProductDao;
import cn.tedu.entitiy.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendServlet",urlPatterns = "/send")
public class SendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集和获取参数
        request.setCharacterEncoding("utf-8");
        String title=request.getParameter("title");
        String author=request.getParameter("author");
        String intro=request.getParameter("intro");
        String categoryId=request.getParameter("categoryId");
        //获取上传文件
        Part filePart=request.getPart("file");
        //获取文件上传信息
        String info=filePart.getHeader("content-disposition");
        //获取文件后缀名
        String suffix = info.substring(info.lastIndexOf("."), info.length() - 1);
        //得到唯一的文件名
        String fileName= UUID.randomUUID()+suffix;
        //得到日期相关的路径
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd/");
        Date date=new Date(); //得到当前时间的日期对象
        String datePath =format.format(date);
        System.out.println(datePath);
        //根据日期路径创建文件夹
        String path=request.getServletContext().getRealPath("images/"+datePath);
        new File(path).mkdirs();
        filePart.write(path+fileName);
        //把参数封装到Product实体类中
        Product product=new Product(0,title,author,intro,"images/"+datePath+fileName,
                0,0,System.currentTimeMillis(),Integer.parseInt(categoryId));
        System.out.println(product);
        ProductDao dao=new ProductDao();
        dao.insert(product);
        response.sendRedirect("/home");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
