package com.cloud.consumer.dao;

import com.cloud.consumer.pojo.Account;
import com.cloud.consumer.pojo.AccountExample;

import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {

    int insert(Account record);

    int insertSelective(Account record);

    BigDecimal findBalance(String userId);

    //修改余额
    int updateBanlance(String userId, BigDecimal totalAmount);

    //行级锁修改余额
    int updateBanlanceBase(String userId,BigDecimal amount);

    //查询余额及版本号
    Account findBalanceObject(String userId);

    //乐观锁修改金额
    int updateBanlanceTwo(String userId, BigDecimal subtract, Integer version);
}