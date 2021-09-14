package top.twhuang.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "admin")
public class Admin extends BaseEntity {

    private String avatar;

    private String username;

    @JsonIgnore
    private String password;

    private String blogTitle;

    private String blogSubtitle;

    private String name;

    private String email;

    private String about;

    private String phone;
}
