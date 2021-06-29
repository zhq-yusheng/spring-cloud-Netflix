package com.yu.cloud.factory;

import com.yu.cloud.service.DeptClientService;
import com.yu.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
/*
服务降级
创建一个DeptClientService工厂类在进行熔断的时候会返回一个DeptClientService对象在这里面进行返回熔断后的操作
这个的降级是在客户端消费者那进行操作DeptClientService这个类最终是会被feign的80端调用
而熔断是在服务的提供者进行操作熔断的操作一般是操作一个方法，而降级缺是直接一个类(这也可以一个方法)
 */

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {

    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            public List<Dept> list() {
                List<Dept>  list = new ArrayList<Dept>();
                list.add(new Dept("服务因服务器a负载过高而实行降级操作断掉了该服务"));
                return list;
            }

            public Dept queryDept(int id) {
                return new Dept("服务因服务器a负载过高而实行降级操作断掉了该服务");
            }

            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
