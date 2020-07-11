package com.cloud.consumer.dao;

import com.cloud.consumer.pojo.Users;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {

    int deleteByPrimaryKey(Integer userid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}