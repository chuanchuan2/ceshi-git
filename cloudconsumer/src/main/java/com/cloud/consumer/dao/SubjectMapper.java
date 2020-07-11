package com.cloud.consumer.dao;

import com.cloud.consumer.pojo.Subject;

import java.util.List;

public interface SubjectMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    //测试mybatis的pageHelper分页插件
    List<Subject> selectAll();

    //测试存储过程查询
    int callSubject(String subjectName, int count);

    //测试存储过程查询根据ID查询学科数据
    Subject searchBySid(String id);
}