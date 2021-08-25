package top.twhuang.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: tw.huang
 * @DateTime: 2020-08-02 11:07
 * @Description: 实体类基类
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @JsonIgnore
    @TableField(exist = false)
    public static final Boolean STATUS_NORMAL = false;

    @JsonIgnore
    @TableField(exist = false)
    public static final Boolean STATUS_DELETED = true;

    /**
     * 删除状态（0正常，1已删除）
     */
    @JsonIgnore
    @TableLogic
    public Boolean delFlag = STATUS_NORMAL;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    public Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    public Date updateTime;

}
