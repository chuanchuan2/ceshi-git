package com.cloud.consumer.service.ipml;

import com.cloud.consumer.dao.AccountMapper;
import com.cloud.consumer.pojo.Account;
import com.cloud.consumer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    //增加金额
    @Override
    public String addAcct(String userId, BigDecimal amount) {
        return changeMoery(userId, amount, 1);
    }

    //减少金额
    @Override
    public String outAcct(String userId, BigDecimal amount) {
        return changeMoery(userId, amount, 2);
    }


    //使用行级锁的方式减少金额
    @Override
    public String outBaseAcct(String userId, BigDecimal amount) {
        return changeMoeryBase(userId, amount, 2);
    }

    //使用行级锁的方式减少金额
    private String changeMoeryBase(String userId, BigDecimal amount, int type) {
        int flag=accountMapper.updateBanlanceBase(userId,amount);
        if (flag>0){
            return "修改金额成功";
        }
        return "修改金额失败";
    }


    //悲观锁
    private String changeMoery(String userId, BigDecimal amount, int type) {

        synchronized (this) {
            //查询账余额
            BigDecimal totalAmount = accountMapper.findBalance(userId);
            if (totalAmount.compareTo(amount) < 0) {
                throw new RuntimeException("账户金额不够");
            }
            //增加金额
            if (type == 1) {
                totalAmount = totalAmount.add(amount);
            } else {
                totalAmount = totalAmount.subtract(amount);
            }
            //修改余额
            int flag=accountMapper.updateBanlance(userId, totalAmount);
                if (flag>0){
                return "修改金额成功";
            }
            return "修改金额失败";
        }
    }

    //乐观锁的方式
    @Override
    public String outBaseAcctTwo(String userId, BigDecimal amount) {
        return changeMoeryTwo(userId, amount);
    }


    //乐观锁的方式
    public String changeMoeryTwo(String userId,BigDecimal account){
       //查询版本号及余额
        Account accountBalance=accountMapper.findBalanceObject(userId);
        if (accountBalance.getMoney().compareTo(account)<0){
            throw new RuntimeException("账户金额不够");
        }else {
           //余额足够
            BigDecimal subtract = accountBalance.getMoney().subtract(account);
            int flag=accountMapper.updateBanlanceTwo(userId, subtract,accountBalance.getVersion());
            if (flag>0){
                return "修改金额成功";
            }
            return "修改金额失败";
        }
    }

}
