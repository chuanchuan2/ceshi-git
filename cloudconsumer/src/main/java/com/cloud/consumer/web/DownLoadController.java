package com.cloud.consumer.web;

import com.cloud.consumer.pojo.Student;
import com.cloud.consumer.service.DownLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("test")
//文件Excle文件下载
public class DownLoadController {

    @Autowired
    DownLoadService downLoadService;

    @RequestMapping("download")
    public String testPage(String id) {
        return "downloadtodata";
    }

    //注意返回的不再是页面类需要加上@ResponseBody注解
    @RequestMapping(value = "returnpojo", method = RequestMethod.GET)
    @ResponseBody //注意返回的不再是页面类需要加上@ResponseBody注解
    public Student returnpojo() {
        Student student = new Student();
        student.setAge("12");
        student.setName("张三");
        return student;
    }

    //注意返回的不再是页面类需要加上@ResponseBody注解
    @RequestMapping(value = "downloadExcle", method = RequestMethod.GET)
    @ResponseBody //注意返回的不再是页面类需要加上@ResponseBody注解
    public void downLoadExcle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        //下载标准模板案例
        downLoadService.downLoadExcle(httpServletRequest,httpServletResponse);
        System.out.println("下载成功！");

    }

}
