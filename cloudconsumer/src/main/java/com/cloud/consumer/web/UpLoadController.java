package com.cloud.consumer.web;

import com.cloud.consumer.pojo.Result;
import com.cloud.consumer.service.DownLoadService;
import com.cloud.consumer.service.UpLoadService;
import com.cloud.consumer.utils.ExcleUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("test")
public class UpLoadController {

    @Autowired
    UpLoadService upLoadService;

    @RequestMapping(value = "upLoadPage", method = RequestMethod.GET)
    public String upLoadPage(String id) {
        return "uploadtodata";
    }

    @RequestMapping(value = "upLoadExcle", method = RequestMethod.POST)
    @ResponseBody //注意返回的不再是页面类需要加上@ResponseBody注解json序列化
    //参数为上传文件的名字个请求
    public void upLoadExcleVoid(MultipartFile uploadfile, HttpServletRequest httpServletRequest) throws IOException {
        //上传文件POI解析案例
        Result result = new Result();
        //1.获取导入文件名称
        String filename = uploadfile.getOriginalFilename();
        //2.校验文件是否存在
        if (StringUtils.isBlank(filename)) {
            result.setCode("0");
            result.setMsg("导入文件不能为空");
            System.out.println(result.toString());
        }
        //2.校验文件是否存在
        if (uploadfile.getSize()>1073741824) {
            result.setCode("0");
            result.setMsg("文件上传超过系统规定10M");
            System.out.println(result.toString());
        }
        //创建workBook
        Workbook workBook = ExcleUtils.createWorkBook(uploadfile.getInputStream(), filename);
        if (null == workBook) {
            result.setCode("0");
            result.setMsg("导入文件只能为Excle文件");
            System.out.println(result.toString());
        }
        //创建行列组装数据并返回
        upLoadService.upLoadExcle(workBook, filename,result);
    }


    @RequestMapping(value = "upLoadExcleParm", method = RequestMethod.POST)
    @ResponseBody //注意返回的不再是页面类需要加上@ResponseBody注解json序列化
    //参数为上传文件的名字个请求
    public Result upLoadExcleParm(MultipartFile uploadfile, HttpServletRequest httpServletRequest) throws IOException {
        //上传文件POI解析案例
        Result result = new Result();
        //1.获取导入文件名称
        String filename = uploadfile.getOriginalFilename();
        //2.校验文件是否存在
        if (StringUtils.isBlank(filename)) {
            result.setCode("0");
            result.setMsg("导入文件不能为空");
        }
        //2.校验文件是否存在
        if (uploadfile.getSize()>1073741824) {
            result.setCode("0");
            result.setMsg("文件上传超过系统规定10M");
            return result;
        }
        //创建workBook
        Workbook workBook = ExcleUtils.createWorkBook(uploadfile.getInputStream(), filename);
        if (null == workBook) {
            result.setCode("0");
            result.setMsg("导入文件只能为Excle文件");
            return result;
        }
        //创建行列组装数据并返回
        return  upLoadService.upLoadExcle(workBook, filename,result);
    }
}
