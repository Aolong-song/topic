package com.example.topic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 宋澳龙
 * @time 2019/12/11 12:00
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = "com.example.topic.mapper")
public class TopicApplication {
    public static void main(String[] args) {
        SpringApplication.run(TopicApplication.class, args);
    }
}
