package me.breakblog.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Comment implements Serializable {
    private Integer id;
    private String author;
    private String email;
    private String site;
    private String body;
    private Date timestamp;
    private Integer fromAdmin;
    private Integer reviewed;
    private Integer repliedId;
    private Integer postId;
    /**
     * 从表实体应该包含一个主表实体的对象引用
     */
    private Post post;
    private Comment comment;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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

    public Integer getFromAdmin() {
        return fromAdmin;
    }

    public void setFromAdmin(Integer fromAdmin) {
        this.fromAdmin = fromAdmin;
    }

    public Integer getReviewed() {
        return reviewed;
    }

    public void setReviewed(Integer reviewed) {
        this.reviewed = reviewed;
    }

    public Integer getRepliedId() {
        return repliedId;
    }

    public void setRepliedId(Integer repliedId) {
        this.repliedId = repliedId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", email='" + email + '\'' +
                ", site='" + site + '\'' +
                ", body='" + body + '\'' +
                ", timestamp=" + timestamp +
                ", fromAdmin=" + fromAdmin +
                ", reviewed=" + reviewed +
                ", repliedId=" + repliedId +
                ", postId=" + postId +
                ", post=" + post +
                ", comment=" + comment +
                '}';
    }
}
