package com.yu.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy // 开启路由网关支持
public class SpringCloudZuul_9527 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZuul_9527.class,args);
    }
}
