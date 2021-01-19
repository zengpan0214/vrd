package cn.tedu.controller;

import cn.tedu.dao.BannerDao;
import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entitiy.Banner;
import cn.tedu.entitiy.Category;
import cn.tedu.entitiy.Product;
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
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "HomeServlet",urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取cid
        String cid=request.getParameter("cid");
        //获取搜索关键字
        String keyword=request.getParameter("keyword");
        //显示首页页面
        Context context=new Context();
        CategoryDao dao=new CategoryDao();
        List<Category> list=dao.findAll();
        context.setVariable("list",list);
        BannerDao bannerDao=new BannerDao();
        List<Banner> BList=bannerDao.findAll();
        context.setVariable("BList",BList);
        //获取Session对象
        HttpSession session=request.getSession();
        //取出保存的用户对象
        User user= (User) session.getAttribute("user");
        /*if (user==null){
            System.out.println("重来没登录过");
        }else{
            System.out.println("已经登录过");
        }*/
        //把用户对象装进容器
        context.setVariable("user",user);
        //查询所有的作品并且装进context容器中
        ProductDao productDao=new ProductDao();
        if(cid!=null){
            //cid有值说明需要查询某个分类的所有作品
            List <Product> pList=productDao.findByCId(cid);
            context.setVariable("pList",pList);
        }else if(keyword!=null){
            List <Product> pList=productDao.findByKeyword(keyword);
            context.setVariable("pList",pList);
        } else {
            List<Product> pList = productDao.findAll();
            context.setVariable("pList",pList);
        }

        //查询浏览最多
        List<Product> vList=productDao.findViewList();
        context.setVariable("vList",vList);
        //查询最受欢迎
        List<Product> lList=productDao.findLikeList();
        context.setVariable("lList",lList);

        ThUtils.print("home.html",context,response);
    }
}
