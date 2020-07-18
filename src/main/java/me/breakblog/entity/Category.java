package me.breakblog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Category implements Serializable {

    private Integer id;

    private String name;
    /**
     * 一对多关系映射，主表实体应该包含从表实体的集合引用
     */
    private List<Post> posts;
}
