package com.xwh;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan(basePackages = {"com.xwh.mapper"})
@SpringBootApplication
@EnableDiscoveryClient
public class WHBlogBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WHBlogBlogApplication.class, args);
    }
}
