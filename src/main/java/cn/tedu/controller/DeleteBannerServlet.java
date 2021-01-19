package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entitiy.Banner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "DeleteBannerServlet",urlPatterns = "/deletebanner")
public class DeleteBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        BannerDao dao=new BannerDao();
        Banner banner=dao.findById(id);
        String path=request.getServletContext().getRealPath(banner.getUrl());
        System.out.println(path);
        new File(path).delete();
        dao.deleteById(id);
        response.sendRedirect("/showbanner");
    }
}
