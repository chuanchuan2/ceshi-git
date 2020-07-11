package com.cloud.consumer.utils;

import org.springframework.util.Assert;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class webUtils {

    public static final String CONTENTTYPE_TEXTHTML = "text/html";
    public static final String CONTENTTYPE_TEXTJSON= "text/json";
    public static final String CONTENTTYPE_TEXTXML = "text/xml";
    public static final String CONTENTTYPE_CHARSET_GBK = "GBK";
    public static final String CONTENTTYPE_CHARSET_GB2312 = "GB2312";
    public static final String CONTENT_CHARSET_UTF8= "UTF-8";

    //写数据到客户端可以指定编码和文件类型
    public static void sendDirecToClient(HttpServletResponse response, String contentType, String charset, String s) {
        Assert.notNull(response);
        String charsetPrefix = WebUtils.CONTENT_TYPE_CHARSET_PREFIX;
        String contentHead = contentType + charsetPrefix + charset;
        response.setContentType(contentHead);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(s == null ? "null" : s);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
