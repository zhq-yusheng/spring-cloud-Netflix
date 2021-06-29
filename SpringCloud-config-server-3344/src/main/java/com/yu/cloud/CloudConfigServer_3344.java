package com.yu.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer // 开启conf配置
public class CloudConfigServer_3344 {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigServer_3344.class,args);
    }
}

