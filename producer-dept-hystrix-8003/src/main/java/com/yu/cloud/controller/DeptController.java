package com.yu.cloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yu.cloud.service.DeptService;
import com.yu.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    /*
    服务熔断这是方法的一一对应在改接口中遇到超时异常错误时就启动备用方法快速的返回结果告诉调用者情况
    而不是卡在这直到服务雪崩
    @HystrixCommand(fallbackMethod = "方法名") 方法报错时调用该方法快速返回结果
     */
    @HystrixCommand(fallbackMethod = "ErryAddDept")
    @GetMapping("/queryDept/{id}")
    public Dept addDept(@PathVariable("id") int id){
        Dept dept = deptService.queryById(id);
        if(dept==null){
            throw new RuntimeException("该部门不存在");
        }
        return dept;
    }

    public Dept ErryAddDept(@PathVariable("id") int id){
        Dept dept = new Dept();
        dept.setDname("Hystrix熔断..部门不存在");
        return dept;
    }




}
