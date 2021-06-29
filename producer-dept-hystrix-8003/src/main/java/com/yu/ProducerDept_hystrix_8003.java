package com.yu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient  // 开启eureka服务
@EnableCircuitBreaker // 开启熔断
public class ProducerDept_hystrix_8003 {
    public static void main(String[] args) {
        SpringApplication.run(ProducerDept_hystrix_8003.class,args);
    }
}
