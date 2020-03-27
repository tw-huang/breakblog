package me.breakblog.entity;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
    private Integer id;
    private String name;
    /**
     * 一对多关系映射，主表实体应该包含从表实体的集合引用
     */
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
