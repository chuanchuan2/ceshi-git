package com.cloud.consumer.service;

import com.cloud.consumer.pojo.Subject;

public interface SeataTestService {

    //通过分布式事务通过fegin新增一门学科数据
    public String feginSeataAdd(Subject subject);
}
