package com.atguigu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 梁志超
 * @version 1.0
 * @time 2023/5/4 14:46
 */
@SpringBootApplication
@ComponentScan("com.atguigu")
public class ServiceAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAuthApplication.class, args);
    }

}
