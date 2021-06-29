package com.yu.cloud.controller;


import com.yu.pojo.Dept;
import com.yu.cloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
public class DeptConsumerController {

    //private static final String PRODUCER_URL = "http://SPRINGCLOD-PRODUCER-DEPT"; // ribbon负载均衡写微服务注册的名字


    /*@Autowired
    RestTemplate restTemplate;*/

    /*
    feign 和 Rest风格的区别就是feign更像是传统的java写法面向接口编程
    但是这就多了一层就会影响效率
     */

    @Resource
    DeptClientService deptService = null; // 类型dubbo的远程调用

    @PostMapping("/add")
    public boolean addDept(Dept dept){
                /* 根据服务者的接口请求类型是post还是get请求 */
        //return restTemplate.postForObject(PRODUCER_URL+"/addDept",dept,boolean.class);
        return deptService.addDept(dept);
    }

    @GetMapping("/queryOne/{id}")
    public Dept queryDept(@PathVariable("id") int id){
        //return restTemplate.getForObject(PRODUCER_URL+"/queryDept/"+id,Dept.class);
        return deptService.queryDept(id);
    }

    @GetMapping("/query")
    public List<Dept> list(){
        //return restTemplate.getForObject(PRODUCER_URL+"/queryList",List.class);
        return deptService.list();
    }
}
