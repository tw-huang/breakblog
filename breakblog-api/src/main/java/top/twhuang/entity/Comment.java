package top.twhuang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "comment")
public class Comment extends BaseEntity {

    @NotNull
    private String author;

    private String email;

    private String site;

    @NotNull
    private String body;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    private Boolean reviewed;

    private Integer repliedId;

    @NotNull
    private Integer postId;

    @TableField(exist = false)
    private Post post;

    @TableField(exist = false)
    private Comment comment;

}
