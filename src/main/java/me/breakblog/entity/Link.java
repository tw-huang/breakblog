package me.breakblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "link")
public class Link extends BaseEntity {

    private String name;

    private String url;

}
