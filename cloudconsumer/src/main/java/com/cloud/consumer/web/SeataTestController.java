package com.cloud.consumer.web;

import com.cloud.consumer.pojo.Subject;
import com.cloud.consumer.service.SeataTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class SeataTestController {
    @Autowired
    private SeataTestService seataTestService;

    @RequestMapping("feginSeataAdd")
    @ResponseBody
    public String add(){
        Subject subject=new Subject();
        subject.setSubjectId(8);
        subject.setSubjectName("思想品德");

        String addResult = seataTestService.feginSeataAdd(subject);
        return addResult;
    }
}
