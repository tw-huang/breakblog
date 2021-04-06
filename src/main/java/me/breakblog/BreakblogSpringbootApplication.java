package me.breakblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BreakblogSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakblogSpringbootApplication.class, args);
    }

}
