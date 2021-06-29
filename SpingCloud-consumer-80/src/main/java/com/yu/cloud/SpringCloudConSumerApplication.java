package com.yu.cloud;


import com.yu.yuRule.YuRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "SPRINGCLOD-PRODUCER-DEPT", configuration = YuRule.class) //name代表要负载均衡的eureka的名字
public class SpringCloudConSumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConSumerApplication.class,args);

    }
}
