package com.cloud.consumer.dao;

import com.cloud.consumer.pojo.Classes;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassesMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);
}