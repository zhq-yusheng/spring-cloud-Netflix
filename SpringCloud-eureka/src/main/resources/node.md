##eureka
eureka是spring cloud Netflix的一个子模块是实现服务注册与发现与zookeeper的区别就是他是实现的restful的http框架不像dubbo的一个RPC框架<br>
###首先导入服务端依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    <version>2.2.6.RELEASE</version>
</dependency>
```
###在编写配置文件
```yaml
server:
  port: 7001
eureka:
  instance:
    hostname: eureka7001.com # eureka的服务端的实例名字
  client:
    register-with-eureka: false # 表示是否向eureka注册中心注册自己
    fetch-registry: false # fetch-registry设置为false表示自己是注册中心
    service-url: # 监控页面 单个配置  http://${eureka.instance.hostname}:${server.port}/eureka/
      defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/ # 集群配置
```
###配置eureka的集群的话就是在defaultZone的属性中设置其他eureka的注册中心的地址用,隔开
###然后在主启动类上加上@EnableEurekaServer注解来开启eureka的注册中心
###在eureka的客户端导入依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
```
###在编写配置文件
```yaml
eureka:
  client:
    service-url: # 单配置  http://localhost:7001/eureka/ # 配置的是要注册的服务中心
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/ #集群配置
  instance:
    instance-id: springClod-producer-dept-8001 # 配置微服务的介绍
    hostname: localhost
    prefer-ip-address: true # 才能自动获取本机url 不配置的话会报错
```
在主启动类上加@EnableEurekaClient开启eureka客户端服务他就会向注册中心注册自己
####导入此依赖在配置就能在eureka的配置中心的info路径下看到自己配置的信息在hystrix的监控中也得导入这个包
```xml
  <!-- 优化配置info的微服务介绍包 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```
```yaml
# 配置info的微服务介绍
info:
  app.name: producer-dept
  author: 钟洪强

```