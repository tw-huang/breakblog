package me.breakblog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: TODO
 * @Date: 2021-03-18 15:41
 * @Author: tw.huang
 * @Version: v1.0.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "file_data")
public class FileData extends BaseEntity{

    private String name;

    private String uuid;

    private String type;

    private Long size;

    private String url;

}
