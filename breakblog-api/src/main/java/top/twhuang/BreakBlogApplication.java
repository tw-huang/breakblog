package top.twhuang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BreakBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakBlogApplication.class, args);
    }

}
