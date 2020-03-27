package me.breakblog.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post implements Serializable {
    private Integer id;
    private String title;
    private String subtitle;
    private String body;
    private Date timestamp;
    private Integer canComment;
    private Integer categoryId;
    private Integer pageView;
    /**
     * 从表实体应该包含一个主表实体的对象引用
     */
    private Category category;

    /**
     * 格式化时间
     *
     * @return 格式化时间
     */
    public String getTimestampFormat() {
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);
        return simpleDateFormat.format(timestamp);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCanComment() {
        return canComment;
    }

    public void setCanComment(Integer canComment) {
        this.canComment = canComment;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", body='" + body + '\'' +
                ", timestamp=" + timestamp +
                ", canComment=" + canComment +
                ", categoryId=" + categoryId +
                ", pageView=" + pageView +
                ", category=" + category +
                '}';
    }
}
