package com.gseem.lesson03;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gseem.lesson03.mapper")
public class Lesson03MybatisAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lesson03MybatisAnnotationApplication.class, args);
    }

}
