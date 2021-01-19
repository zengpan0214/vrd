package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entitiy.Banner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendBannerServlet",urlPatterns = "/sendbanner")
public class SendBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Part filePart=request.getPart("file");

        String info=filePart.getHeader("content-disposition");
        System.out.println(info);

        String suffix=info.substring(info.lastIndexOf("."),info.length()-1);
        String fileName= UUID.randomUUID()+suffix;
        System.out.println("fileName="+fileName);
        String path=request.getServletContext().getRealPath("images/");
        System.out.println("path="+path);

        filePart.write(path+fileName);
        BannerDao dao=new BannerDao();
        dao.insert(new Banner(0,"images/"+fileName));

        response.sendRedirect("/showbanner");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
