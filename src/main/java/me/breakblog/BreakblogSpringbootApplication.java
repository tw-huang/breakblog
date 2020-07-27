package me.breakblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("me.breakblog.mapper")
public class BreakblogSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakblogSpringbootApplication.class, args);
    }

}
