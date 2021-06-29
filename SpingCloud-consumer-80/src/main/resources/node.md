#此服务是服务的消费者使用Netflix的rest编程方式进行调用，还有用ribbon做客户端的负载均衡
##Rest风格调用其他服务
###因为他的服务要去eureka注册中心拿服务得导入eureka的依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
```
###编写的eureka(这里是配置的集群也可以用集群就写一个就好)然后在主启动类上加上@EnableEurekaClient开启eureka的注解
```yaml
# eureka配置
eureka:
  client:
    register-with-eureka: false # 因为是客户端，不向注册中心注册自己
    service-url:  # 配置要向哪个地方拿服务
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
```
###在注入一个RestTemplate类
```java
@Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
```
###在controller层自动装配进RestTemplate类使用RestTemplate的xxxForObject(url,参数+返回对象的反射对象)就会返回服务的对象
```java
//private static final String PRODUCER_URL = "http://localhost:8001/"; 这种方法是直接写死，用ribbon负载均衡就不能这么写
private static final String PRODUCER_URL = "http://SPRINGCLOD-PRODUCER-DEPT"; // ribbon负载均衡写微服务注册的名字
    
@Autowired
RestTemplate restTemplate;

    @PostMapping("/add")
    public boolean addDept(Dept dept){
                /* 根据服务者的接口请求类型是post还是get请求postForObject方法有重载没有参数的可以不写 */
        return restTemplate.postForObject(PRODUCER_URL+"/addDept",dept,boolean.class);
    }
```
##ribbon客户端负载均衡
###导入依赖
```xml
<!--ribbon负载均衡包 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-ribbon</artifactId>
    <version>2.2.6.RELEASE</version>
</dependency>
```
###在注入restTemplate的方法上加入@LoadBalanced注解就能默认的实现轮询的负载均衡
###自定义负载均衡规则不能在主启动类的同级目录上建包然后编写规则在驱动类上加入@RibbonClient(name = "SPRINGCLOD-PRODUCER-DEPT", configuration = YuRule.class) //name代表要负载均衡的eureka的名字