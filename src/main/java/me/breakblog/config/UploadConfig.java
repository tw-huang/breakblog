package me.breakblog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 上传配置
 * @Date: 2021-03-18 14:54
 * @Author: tw.huang
 * @Version: v1.0.0
 **/
@ConfigurationProperties(prefix = "app.upload")
@Component
@Data
public class UploadConfig {

    public static final String TYPE_LOCAL = "local";

    //上传方式 (local/cos/oss)
    private String type;
    //地址
    private String path;
    //HOST
    private String host;
}
