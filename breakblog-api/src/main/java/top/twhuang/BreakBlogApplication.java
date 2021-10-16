package top.twhuang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import top.twhuang.config.UploadConfig;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(value = {UploadConfig.class})
public class BreakBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakBlogApplication.class, args);
    }

}
