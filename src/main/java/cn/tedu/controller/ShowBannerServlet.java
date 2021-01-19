package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.entitiy.Banner;
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

@WebServlet(name = "ShowBannerServlet",urlPatterns = "/showbanner")
public class ShowBannerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BannerDao dao=new BannerDao();
        List<Banner> bList = dao.findAll();
        System.out.println(bList);
        Context context =new Context();
        context.setVariable("bList",bList);
        ThUtils.print("banner.html",context,response);
    }
}
