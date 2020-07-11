package com.cloud.consumer.service;

import java.math.BigDecimal;

public interface AccountService {
    //增加金额
    public String addAcct(String userId, BigDecimal amount);

    //减少金额
    public String outAcct(String userId, BigDecimal amount);

    public String outBaseAcct(String userId, BigDecimal amount);
    //乐观锁
    public String outBaseAcctTwo(String userId, BigDecimal amount);
}
