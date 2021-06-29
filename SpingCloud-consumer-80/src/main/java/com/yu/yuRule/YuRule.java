package com.yu.yuRule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YuRule {

    @Bean
    public IRule getRule(){
        return new YuRoundRobinRule(); // 将我们自己写的类注入进来
    }
}
