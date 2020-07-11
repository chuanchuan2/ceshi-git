package com.cloud.consumer.web;

import com.cloud.consumer.client.ConsumerClient;
import com.cloud.consumer.pojo.Student;
import com.cloud.consumer.pojo.Subject;
import com.cloud.consumer.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
//消费者和生产者使用feign来进行通讯测试
public class FeginTestController {

    @Autowired
    private ConsumerClient consumerClient;
    @Autowired
    private SubjectService subjectService;

    //直接访问测试
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(String id) {
        return "1111";
    }

    //通过fegin访问测试
    @RequestMapping(value = "/feginSearch", method = RequestMethod.POST)
    public String feginSearch(String id) {
        System.out.println("进来了。。。。。");
        String s = consumerClient.testConsummer(id);
        return s;
    }

    //通过fegin访问测试返回实体
    @RequestMapping(value = "/feginPojo", method = RequestMethod.POST)
    public Student feginPojo(String id) {
        System.out.println("通过fegin访问测试" + id);
        Student student = consumerClient.feginPojo(id);
        System.out.println("通过fegin访问测试");
        System.out.println(student.toString());
        return student;
    }

    //直接访问测试数据库连接
    @RequestMapping(value = "/searchmysql", method = RequestMethod.GET)
    public Subject searchMySql(String id) {
        return subjectService.searchMySqlDate(id);
    }

    //通过fegin访问生产者测试数据库连接
    @RequestMapping(value = "/searchDateBase", method = RequestMethod.POST)
    public Subject searchDateBase(String id) {
        return consumerClient.searchDateBase(id);
    }

    //测试存储过程查询
    @RequestMapping(value = "/searchprocedure", method = RequestMethod.GET)
    public String searchProcedure(String subjectName) {
        int count = subjectService.searchMySqlPprocedure(subjectName);
        String scount = Integer.valueOf(count).toString();
        return scount;
    }

    //测试存储过程查询根据ID查询学科数据
    @RequestMapping(value = "/searchBySid", method = RequestMethod.GET)
    public Subject searchBySid(String id) {
        Subject subject= subjectService.searchBySid(id);
        return subject;
    }
}
