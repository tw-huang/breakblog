package me.breakblog.entity;

import java.io.Serializable;

public class Admin implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String blogTitle;
    private String blogSubtitle;
    private String name;
    private String about;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogSubtitle() {
        return blogSubtitle;
    }

    public void setBlogSubtitle(String blogSubtitle) {
        this.blogSubtitle = blogSubtitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogSubtitle='" + blogSubtitle + '\'' +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
