package com.cloud.consumer.service;

public interface RedisTestService {

    //Redis新增测试
    public int redisAdd(String add);

    //Redis获取值测试
    String redisGet(String key);
}
