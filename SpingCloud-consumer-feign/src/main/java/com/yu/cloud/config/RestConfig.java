package com.yu.cloud.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @LoadBalanced // 因为走的http拿的数据 所以在这方法上加上该注解就能实现负载均衡(默认实现的是轮询(y一个一个的实现))
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /*@Bean
    public IRule getRandomRule(){ // 覆盖默认的轮询使用随机的负载均衡算法
        return new RandomRule();
    } 注释掉是因为我们要进行自定义的负载均衡他不能和主启动类同级，不能被扫描到
    */
}
