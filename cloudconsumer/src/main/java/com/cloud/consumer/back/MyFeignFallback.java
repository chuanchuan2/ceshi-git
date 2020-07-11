package com.cloud.consumer.back;

import com.cloud.consumer.client.ConsumerClient;
import com.cloud.consumer.pojo.Student;
import com.cloud.consumer.pojo.Subject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/myfeign")
/*这里要重点强调的是@RequestMapping URL路径映射的问题，如果
@RequestMapping的值和ConsumerClient的接口相同，会导致同一个URL映射到两个方法上，
 因此需要保证Fallback类的@RequestMapping的值与interface不同*/
public class MyFeignFallback implements ConsumerClient {

    //通过fegin访问测试Fallback
    @Override
    public String testConsummer(String id) {
        return null;
    }

    //通过fegin访问测试返回实体Fallback
    @Override
    public Student feginPojo(String id) {
        System.out.println("000000000000000000000000000000000000000");
        Student student=new Student();
        student.setName("奶奶的沙丘没得！");
        student.setAge("100");
        return student;
    }

    @Override
    public Subject searchDateBase(String id) {
        Subject subject=new Subject();
        subject.setSubjectId(0);
        subject.setSubjectName("奶奶的沙丘学科没得！");
        return null;
    }

    @Override
    public String feginSeataAdd(Subject subject) {
        return "进入fallback，学科新增失败！";
    }
}
