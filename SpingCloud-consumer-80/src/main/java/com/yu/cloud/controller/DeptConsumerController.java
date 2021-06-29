package com.yu.cloud.controller;


import com.yu.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
public class DeptConsumerController {

    //private static final String PRODUCER_URL = "http://localhost:8001/"; 这种方法是直接写死，用ribbon负载均衡就不能这么写
    private static final String PRODUCER_URL = "http://SPRINGCLOD-PRODUCER-DEPT"; // ribbon负载均衡写微服务注册的名字


    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/add")
    public boolean addDept(Dept dept){
                /* 根据服务者的接口请求类型是post还是get请求 */
        return restTemplate.postForObject(PRODUCER_URL+"/addDept",dept,boolean.class);
    }

    @GetMapping("/queryOne/{id}")
    public Dept addDept(@PathVariable("id") int id){
        return restTemplate.getForObject(PRODUCER_URL+"/queryDept/"+id,Dept.class);
    }

    @GetMapping("/query")
    public List<Dept> list(){
        return restTemplate.getForObject(PRODUCER_URL+"/queryList",List.class);
    }
}
