package com.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//这个注解可以作为任何注册中心的客户端，最开始使用的是@EnableEurekaClient只能作为Eureka的客户端被淘汰了
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.cloud.consumer.dao")
//@ComponentScan("com.cloud.consumer.dao")  注意只能用上面的扫面这种是错误的，会导致页面无法访问
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}


//eureka学习总结
/*
1.在应用主类中配置@EnableDiscoveryClient注解。
2.在application.yml中用eureka.client.serviceUrl.defaultZone参数指定了服务注册中心service的位置。
*/
