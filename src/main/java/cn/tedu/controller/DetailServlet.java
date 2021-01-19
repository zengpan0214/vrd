package cn.tedu.controller;

import cn.tedu.dao.CategoryDao;
import cn.tedu.dao.ProductDao;
import cn.tedu.entitiy.Category;
import cn.tedu.entitiy.Product;
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

@WebServlet(name = "DetailServlet",urlPatterns = "/detail")
public class DetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        ProductDao dao=new ProductDao();

        HttpSession session=request.getSession();
        String viemId=(String) session.getAttribute("view"+id);
        if (viemId==null){
            dao.viewById(id);
            session.setAttribute("view"+id,id);
        }

        Product product=dao.findById(id);
        System.out.println("product="+product);

        Context context=new Context();
        context.setVariable("product",product);

        //添加分类信息
        CategoryDao cDao= new CategoryDao();
        List<Category> list = cDao.findAll();
        context.setVariable("list",list);
        //查询浏览最多
        List<Product> vList=dao.findViewList();
        context.setVariable("vList",vList);
        //查询最受欢迎
        List<Product> lList=dao.findLikeList();
        context.setVariable("lList",lList);
        //把登录的用户对象添加到容器中
        context.setVariable("user",request.getSession().getAttribute("user"));

        ThUtils.print("detail.html",context,response);
    }
}
