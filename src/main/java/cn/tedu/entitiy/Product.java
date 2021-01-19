package cn.tedu.entitiy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product {
    private int id;
    private String title;
    private String author;
    private String intro;
    private String url;
    private int viewCount;
    private int likeCount;
    private long created;
    private int categoryId;
    public String createdStr(){
        //日期格式化
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //根据发布时间戳 创建日期对象
        Date date=new Date(this.created);
        //把日期对象转成核定格式字符串时间
        return f.format(date);
    }

    public Product(int id, String title, String author, String intro, String url, int viewCount, int likeCount, long created, int categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.intro = intro;
        this.url = url;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.created = created;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", intro='" + intro + '\'' +
                ", url='" + url + '\'' +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", created=" + created +
                ", categoryId=" + categoryId +
                '}';
    }
}
