package cn.tedu.dao;

import cn.tedu.entitiy.Product;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public void insert(Product product) {
        //获取连接
        try (Connection conn = DBUtils.getConn()) {
            String sql = "insert into product values(null,?,?,?,?,0,0,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getAuthor());
            ps.setString(3, product.getIntro());
            ps.setString(4, product.getUrl());
            ps.setLong(5, product.getCreated());
            ps.setInt(6, product.getCategoryId());
            ps.executeUpdate();
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> findAll() {
        ArrayList<Product> list = new ArrayList<>();
        //获取连接
        try (Connection conn = DBUtils.getConn()) {
            String sql = "select*from product limit 0,5";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url = rs.getString(5);
                int viewCount = rs.getInt(6);
                int likeCount = rs.getInt(7);
                Long created = rs.getLong(8);
                int categoryId = rs.getInt(9);
                list.add(new Product(id, title, author, intro, url, viewCount, likeCount, created, categoryId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findViewList() {
        ArrayList<Product> list = new ArrayList<>();
        //获取连接
        try (Connection conn = DBUtils.getConn()) {
            String sql = "select * from product order by viewCount desc limit 0,4";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url = rs.getString(5);
                int viewCount = rs.getInt(6);
                int likeCount = rs.getInt(7);
                Long created = rs.getLong(8);
                int categoryId = rs.getInt(9);
                list.add(new Product(id, title, author, intro, url, viewCount, likeCount, created, categoryId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findLikeList() {
        ArrayList<Product> list = new ArrayList<>();
        //获取连接
        try (Connection conn = DBUtils.getConn()) {
            String sql = "select * from product order by likeCount desc limit 0,4";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url = rs.getString(5);
                int viewCount = rs.getInt(6);
                int likeCount = rs.getInt(7);
                Long created = rs.getLong(8);
                int categoryId = rs.getInt(9);
                list.add(new Product(id, title, author, intro, url, viewCount, likeCount, created, categoryId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Product> findByCId(String cid) {
        ArrayList<Product> list = new ArrayList<>();
        //获取连接
        try (Connection conn = DBUtils.getConn()) {
            String sql = "select*from product where category_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(cid));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url = rs.getString(5);
                int viewCount = rs.getInt(6);
                int likeCount = rs.getInt(7);
                Long created = rs.getLong(8);
                int categoryId = rs.getInt(9);
                list.add(new Product(id, title, author, intro, url, viewCount, likeCount, created, categoryId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Product> findByKeyword(String keyword) {
        ArrayList<Product> list = new ArrayList<>();
        //获取连接
        try (Connection conn = DBUtils.getConn()) {
            String sql = "select*from product where title like ? or author like ? or intor like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setString(3, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url = rs.getString(5);
                int viewCount = rs.getInt(6);
                int likeCount = rs.getInt(7);
                Long created = rs.getLong(8);
                int categoryId = rs.getInt(9);
                list.add(new Product(id, title, author, intro, url, viewCount, likeCount, created, categoryId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product findById(String id) {
        //获取连接
        try (Connection conn = DBUtils.getConn()) {
            String sql = "select*from product where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url = rs.getString(5);
                int viewCount = rs.getInt(6);
                int likeCount = rs.getInt(7);
                Long created = rs.getLong(8);
                int categoryId = rs.getInt(9);
                return new Product(Integer.parseInt(id), title,
                        author, intro, url, viewCount, likeCount, created, categoryId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void likeById(String id) {
        //获取连接
        try (Connection conn = DBUtils.getConn()) {
            String sql = "update product set likeCount=likeCount+1 where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewById(String id) {
        //获取连接
        try (Connection conn = DBUtils.getConn()) {
            String sql = "update product set viewCount=viewCount+1 where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(String id) {
        //获取连接
        try (Connection conn = DBUtils.getConn()) {
            String sql = "delete from product where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> loadMore(String count) {
        ArrayList<Product> list = new ArrayList<>();
        //获取连接
        try (Connection conn = DBUtils.getConn()) {
            String sql = "select*from product limit ?,5";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(count));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String author = rs.getString(3);
                String intro = rs.getString(4);
                String url = rs.getString(5);
                int viewCount = rs.getInt(6);
                int likeCount = rs.getInt(7);
                Long created = rs.getLong(8);
                int categoryId = rs.getInt(9);
                list.add(new Product(id, title, author, intro, url, viewCount, likeCount, created, categoryId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}