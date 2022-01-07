package com.example.project1;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.example.project1.dao")
public class Project1Application {
    public static void main(String[] args) {
        SpringApplication.run(Project1Application.class, args);

    }
}
