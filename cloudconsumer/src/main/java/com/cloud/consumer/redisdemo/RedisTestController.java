package com.cloud.consumer.redisdemo;

import com.cloud.consumer.service.RedisTestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class RedisTestController {

    @Autowired
    private RedisTestService redisTestService;

    @RequestMapping("redisAdd")
    @ResponseBody
    public String redisAdd(String add){
        int flag=redisTestService.redisAdd(add);
        if (flag>0){
            return "新增成功！";
        }else {
            return "新增失败！";
        }
    }

    //Redis获取值测试
    @RequestMapping("redisGet")
    @ResponseBody
    public String redisGet(String key){
        String value=redisTestService.redisGet(key);
        if (StringUtils.isBlank(value)){
            return "未获取到值！";
        }else {
            return "获取到值为"+value;
        }
    }
}
