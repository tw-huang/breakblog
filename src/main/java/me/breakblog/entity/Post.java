package me.breakblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Post implements Serializable {

    private Integer id;

    private String title;

    private String subtitle;

    private String body;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timestamp;

    private Integer canComment;

    private Integer categoryId;

    private Integer pageView;
    /**
     * 从表实体应该包含一个主表实体的对象引用
     */
    private Category category;
}
