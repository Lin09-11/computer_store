package com.example.mycomputerstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//MapperScan注解指定当前项目中Mapper接口路径的位置，在项目启动的时候自动加载所有的接口
@MapperScan("com.example.mycomputerstore.mapper")
public class MyComputerStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyComputerStoreApplication.class, args);
    }

}
