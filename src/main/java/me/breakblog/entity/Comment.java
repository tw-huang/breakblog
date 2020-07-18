package me.breakblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class Comment implements Serializable {

    private Integer id;

    private String author;

    private String email;

    private String site;

    private String body;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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

}
