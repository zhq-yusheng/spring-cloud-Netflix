#hystrix页面实时接口访问监控
##先导入hystrix的依赖
```xml
 <!-- hystrix包 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-hystrix</artifactId>
        <version>1.4.7.RELEASE</version>
    </dependency>
    <!-- hystrix监控页面包 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
        <version>1.4.7.RELEASE</version>
    </dependency>
```
##编写配置文件
```yaml
server:
  port: 9001
# 必须配置这个不然页面监控出错
hystrix:
  dashboard:
    proxy-stream-allow-list: localhost
```
##在主函数入口加上@EnableHystrixDashboard注解来开启监控页面
启动类在浏览器上访问http://localhost:9001/hystrix是否有页面
##在要被监控的的服务中必须要导入以下两个依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency> 
```
##在要被监控的controller的方法上加上@HystrixCommand注解才能进行监控当然这个加了也得在主启动类上开启熔断支持@EnableCircuitBreaker
##在注入一个servlet一般是写死的(这个servlet是来获取当前服务的流的到时候要把地址给hystrix的监控页面)
```java
 // 监控流此服务的监控流
    @Bean
public ServletRegistrationBean getHystrixMetricsStreamServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        bean.addUrlMappings("/actuator/hystrix.stream");
        return bean;
    }
```
##启动类访问http://localhost:监控服务的端口号/actuator/hystrix.stream 看是否有数据，没数据先掉接口在看是否有数据
在http://localhost:9001/hystrix的页面中将http://localhost:监控服务的端口号/actuator/hystrix.stream地址给他进行实时监控