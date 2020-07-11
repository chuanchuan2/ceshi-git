package com.cloud.consumer.client;

import com.cloud.consumer.back.MyFeignFallback;
import com.cloud.consumer.pojo.Student;
import com.cloud.consumer.pojo.Subject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Vector;

@Component
@FeignClient(value = "ly-cloudproduct",fallback = MyFeignFallback.class)
@RequestMapping("find")
public interface ConsumerClient {

    //通过fegin访问测试
    @RequestMapping(value = "/feginSearch",method = RequestMethod.POST )
    public String testConsummer(@RequestParam("id") String id);

    //通过fegin访问测试返回实体
    @RequestMapping(value = "/feginPojo",method = RequestMethod.POST )
    public Student feginPojo(@RequestParam("id") String id);

    //通过fegin访问生产者测试数据库连接
    @RequestMapping(value = "/feginPojoDateBase", method = RequestMethod.POST)
    public Subject searchDateBase(@RequestParam("id") String id);

    //通过分布式事务通过fegin新增一门学科数据
    @RequestMapping(value = "/feginSeataAdd", method = RequestMethod.POST,consumes = "application/json")
    public String feginSeataAdd(@RequestBody Subject subject);

}
