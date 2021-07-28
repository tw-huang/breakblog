package top.twhuang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BreakblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakblogApplication.class, args);
    }

}
