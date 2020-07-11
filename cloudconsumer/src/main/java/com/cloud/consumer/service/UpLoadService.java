package com.cloud.consumer.service;

import com.cloud.consumer.pojo.Result;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UpLoadService {

    //文件上传测试
    Result upLoadExcle(Workbook workBook, String filename,Result result);
}
