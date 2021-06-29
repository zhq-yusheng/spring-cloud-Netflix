package com.yu.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SpringCloudClientController {

    @Value("${server.port}")
    private String port; // 获取远程配置文件的端口号

    @Value("${eureka.client.service-url.defaultZone}")
    private  String eurekaUrl; // 获取远程配置文件的eureka集群url

    @Value("${spring.application.name}")
    private String applicationName; // 获取远程配置文件的项目名称


    @GetMapping("/config")
    public Map<String,Object> getConfig(){
        Map<String,Object> maps = new HashMap<String, Object>();
        maps.put("port",port);
        maps.put("eurekaUrl",eurekaUrl);
        maps.put("applicationName",applicationName);
        return maps;

    }

}
