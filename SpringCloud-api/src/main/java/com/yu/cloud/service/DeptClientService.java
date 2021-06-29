package com.yu.cloud.service;


import com.yu.cloud.factory.DeptClientServiceFallbackFactory;
import com.yu.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
                        //value属性对应eureka当中注册的名字 相当于在哪个地方去拿这个在feign中会装配该接口，该接口会自动生成实现类
@Component// 将自己写的工程类导进来当这个服务关闭后就会返回这个工厂类的逻辑操作 这边写后会去掉方法的服务中加配置开启降级操作
@FeignClient(value = "SPRINGCLOD-PRODUCER-DEPT",fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
    @GetMapping("/queryList")
     List<Dept> list();
    @GetMapping("/queryDept/{id}")
     Dept queryDept(@PathVariable("id") int id);
    @PostMapping("/addDept")
     boolean addDept(Dept dept);
}
