package com.cloud.product.web;

import com.cloud.product.pojo.Student;
import com.cloud.product.pojo.Subject;
import com.cloud.product.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/find")
public class productContrller {

    @Autowired
    private ProductService productService;

    //使用fegin方法名可不一样
    @RequestMapping("/feginSearch")
    public String testMethod(String id) {
        return productService.testConsummer(id);
    }

    //通过fegin访问测试返回实体
    @RequestMapping(value = "/feginPojo", method = RequestMethod.POST)
    public Student feginPojo(String id) {
        Student student = new Student();
        System.out.println("++++++++++++++++++++++++" + id);
        System.out.println(StringUtils.equals("1", id));
        System.out.println(1/0);
        System.out.println("-==================");
        student.setAge("18");
        student.setName("zhangsan");
        return student;
    }


    //通过fegin访问数据库测试返回实体
    @RequestMapping(value = "/feginPojoDateBase", method = RequestMethod.POST)
    public Subject feginPojoDateBase(String id) {
        return productService.searchDateBase(id);
    }

    ///通过分布式事务通过fegin新增一门学科数据
    @RequestMapping(value = "/feginSeataAdd", method = RequestMethod.POST)
    public String feginSeataAdd(@RequestBody Subject subject) {
        return productService.feginSeataAdd(subject);
    }

    public static void main(String[] args) {
        System.out.println("111");
    }
}
