package com.example.mycomputerstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@Configuration
//MapperScan注解指定当前项目中Mapper接口路径的位置，在项目启动的时候自动加载所有的接口
@MapperScan("com.example.mycomputerstore.mapper")
public class MyComputerStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyComputerStoreApplication.class, args);
    }

    /**
     * 设置文件上传大小
     * @return
     */
    @Bean
    public MultipartConfigElement getMultipartConfigElement(){
        //创建一个配置的工厂类对象
        MultipartConfigFactory factory = new MultipartConfigFactory();

        //设置需要创建的对象的相关信息
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(15,DataUnit.MEGABYTES));
        //通过工厂类来创建MulitpartConfigElement对象
        return factory.createMultipartConfig();
    }

}
