package com.cloud.product.service;

import com.cloud.product.pojo.Student;
import com.cloud.product.pojo.Subject;

public interface ProductService {
    //通过fegin访问测试返回实体
    public String testConsummer(String id);

    //通过fegin访问数据库测试返回实体
    Subject searchDateBase(String id);

    //通过分布式事务通过fegin新增一门学科数据
    String feginSeataAdd(Subject subject);
}
