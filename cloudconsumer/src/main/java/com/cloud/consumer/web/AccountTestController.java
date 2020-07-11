package com.cloud.consumer.web;

import com.cloud.consumer.client.ConsumerClient;
import com.cloud.consumer.pojo.Subject;
import com.cloud.consumer.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping("test")
public class AccountTestController {

    @Autowired
    private AccountService accountService;

    //数据库悲观锁测试
    @RequestMapping(value = "/addBanlance", method = RequestMethod.GET)
    @ResponseBody
    public String addAcct(String userId,String banlance) {
        String s = accountService.addAcct(userId, new BigDecimal(banlance));
        return s;
    }

    //数据库悲观锁测试
    @RequestMapping(value = "/outBanlance", method = RequestMethod.GET)
    @ResponseBody
    public String outAcct(String userId,String banlance) {
        String s = accountService.outAcct(userId, new BigDecimal(banlance));
        return s;
    }

    //数据库行级锁修改金额
    @RequestMapping(value = "/outBaseBanlance", method = RequestMethod.GET)
    @ResponseBody
    public String outBaseAcct(String userId,String banlance) {
        String s = accountService.outBaseAcct(userId, new BigDecimal(banlance));
        return s;
    }

    //数据库乐观锁修改金额
    @RequestMapping(value = "/outBaseBanlanceTow", method = RequestMethod.GET)
    @ResponseBody
    public String outBaseBanlanceTow(String userId,String banlance) {
        String s = accountService.outBaseAcctTwo(userId, new BigDecimal(banlance));
        return s;
    }
}
