package com.cloud.product.dao;


import com.cloud.product.pojo.Subject;

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
}