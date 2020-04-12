package com.maozi.easywork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.maozi.easywork.mapper")
@SpringBootApplication
public class EasyworkApplication {


    public static void main(String[] args) {
        SpringApplication.run(EasyworkApplication.class, args);
    }

}
