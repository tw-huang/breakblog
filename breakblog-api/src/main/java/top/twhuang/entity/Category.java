package top.twhuang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
@TableName(value = "category")
public class Category extends BaseEntity {

    private String name;

    @TableField(exist = false)
    private Integer postCount;
}
