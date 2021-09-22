package top.twhuang.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "link")
public class Link extends BaseEntity {

    private String name;

    private String url;

}
