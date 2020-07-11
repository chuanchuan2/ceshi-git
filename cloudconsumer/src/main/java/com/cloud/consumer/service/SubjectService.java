package com.cloud.consumer.service;

import com.cloud.consumer.pojo.Subject;

import java.util.List;

public interface SubjectService {
    //校验数据库是否配置连接成功
    Subject searchMySqlDate(String id);

    //测试mybatis的pageHelper分页插件
    List<Subject> subjectList(String page, String rows);

    //测试存储过程查询
    int searchMySqlPprocedure(String subjectName);

    //测试存储过程查询根据ID查询学科数据
    Subject searchBySid(String id);
}
