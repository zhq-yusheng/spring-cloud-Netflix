package com.yu.yuRule;


import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义负载均衡算法 实现IRule接口重写方法
 * 这是把轮询的类拷来自己修改
 * 这自定义每次服务走五次在换下一个服务
 */
public class YuRoundRobinRule extends AbstractLoadBalancerRule {
    private AtomicInteger nextServerCyclicCounter;
    private static final boolean AVAILABLE_ONLY_SERVERS = true;
    private static final boolean ALL_SERVERS = false;

    private int getCount = 0; // 请求的统计
    private int serverIndex = 0; // 指针取服务的下标

    private static Logger log = LoggerFactory.getLogger(YuRoundRobinRule.class);

    public YuRoundRobinRule() {
        nextServerCyclicCounter = new AtomicInteger(0);
    }

    public YuRoundRobinRule(ILoadBalancer lb) {
        this();
        setLoadBalancer(lb);
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers(); // 获取还活着的服务
            List<Server> allServers = lb.getAllServers();       // 获取全部的服务(包括已经挂掉的)
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            /*
            int nextServerIndex = incrementAndGetModulo(serverCount);
            server = allServers.get(nextServerIndex);
            */

            //--------------------------自定义负载均衡规则---------------------
            if(getCount < 5){
                getCount ++;
                server = reachableServers.get(serverIndex);
            }else{
                getCount = 0; // 重置请求的统计并且索引加一
                serverIndex++;
                if (serverIndex >= (upCount - 1)){ // 判断索引长度和服务的长度,如果都走完了就重置
                    serverIndex = 0;
                }
                server = reachableServers.get(serverIndex);
            }


            //---------------------------------------------------------------

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }

    /**
     * Inspired by the implementation of {@link AtomicInteger#incrementAndGet()}.
     *
     * @param modulo The modulo to bound the value of the counter.
     * @return The next value.
     */
    private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }


    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }


    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}

