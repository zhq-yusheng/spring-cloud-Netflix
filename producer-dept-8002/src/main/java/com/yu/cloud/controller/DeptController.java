package com.yu.cloud.controller;


import com.yu.pojo.Dept;
import com.yu.cloud.service.DeptService;
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

    @Autowired
    DiscoveryClient client;

    @PostMapping("/addDept")
    public boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/queryDept/{id}")
    public Dept addDept(@PathVariable("id") int id){
        return deptService.queryById(id);
    }

    @GetMapping("/queryList")
    public List<Dept> list(){
        return deptService.queryAll();
    }


    // 获取微服务的信息
    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = client.getServices(); // 可以获取到服务的清单
        System.out.println("微服务清单discovery ==>"+services);
        // 通过微服务的名字获取到微服务的信息 一般联合开发的时候用得到
        List<ServiceInstance> instances = client.getInstances("springClod-producer-dept-8001");
        for (ServiceInstance instance : instances) {
            System.out.println(
                            "服务主机: "+instance.getHost()+
                            "端口: " + instance.getPort()+
                            "url地址: " + instance.getUri()+
                            "实例名称: " + instance.getInstanceId()+
                            "服务名称: " + instance.getServiceId()
            );
        }
        return instances;
    }


}
