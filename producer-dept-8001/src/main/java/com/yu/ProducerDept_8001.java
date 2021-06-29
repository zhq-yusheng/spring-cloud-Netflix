package com.yu;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.hystrix.metric.HystrixCollapserEventStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient  // 开启eureka服务
@EnableCircuitBreaker
public class ProducerDept_8001 {
    public static void main(String[] args) {
        SpringApplication.run(ProducerDept_8001.class,args);
    }

    // 监控流此服务的监控流
    @Bean
    public ServletRegistrationBean getHystrixMetricsStreamServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        bean.addUrlMappings("/actuator/hystrix.stream");
        return bean;
    }
}
