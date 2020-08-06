package me.breakblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "admin")
public class Admin extends BaseEntity {

    private String username;

    private String password;

    private String blogTitle;

    private String blogSubtitle;

    private String name;

    private String about;

    private String email;
}
