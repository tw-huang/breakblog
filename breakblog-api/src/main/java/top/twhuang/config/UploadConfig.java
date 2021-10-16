package top.twhuang.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 上传配置
 * @Date: 2021-03-18 14:54
 * @Author: tw.huang
 * @Version: v1.0.0
 **/
@ConfigurationProperties(prefix = "breakblog.upload")
@Setter
@Getter
public class UploadConfig {

    public static final String TYPE_LOCAL = "local";

    /**
     * 上传方式 (local/cos/oss)
     */
    private String type;

    /**
     * 路径
     */
    private String path;

    /**
     * host
     */
    private String host;
}
