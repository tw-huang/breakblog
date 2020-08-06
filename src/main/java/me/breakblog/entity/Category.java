package me.breakblog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName(value = "category")
public class Category extends BaseEntity{

    private String name;

    @TableField(exist = false)
    private List<Post> posts;
}
