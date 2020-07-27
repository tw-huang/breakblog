package me.breakblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@TableName(value = "comment")
public class Comment implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
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

    @TableField(exist = false)
    private Post post;

    @TableField(exist = false)
    private Comment comment;

}
