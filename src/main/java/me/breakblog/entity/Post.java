package me.breakblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@TableName(value = "post")
public class Post implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String title;

    private String subtitle;

    private String body;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date timestamp;

    private Integer canComment;

    private Integer categoryId;

    private Integer pageView;

    @TableField(exist = false)
    private Category category;
}
