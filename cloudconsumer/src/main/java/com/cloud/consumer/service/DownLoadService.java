package com.cloud.consumer.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DownLoadService {

    //下载测试
    public void downLoadExcle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
