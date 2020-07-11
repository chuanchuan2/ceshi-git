package com.cloud.consumer.utils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ResultUtils {
    //返回的标志为布尔值
    public static final Boolean YES = true;
    //返回的标志不为布尔值
    public static final Boolean NO = true;
    //成功
    public static final String TRUE = "true";
    //失败
    public static final String FALSE = "false";

    //返回对象
    public static void sendObject(HttpServletResponse response, Object object) {
        String result = null;
        //将BigDecimal类型转换
        try {
            result = JSONObjectUtils.formObjectForBigDecimal(object);
        } catch (Exception e) {
            result = JSONObjectUtils.formArrayForBigDecimal(object);
        }
        //将数据写到前端页面
        webUtils.sendDirecToClient(response, webUtils.CONTENTTYPE_TEXTJSON, webUtils.CONTENT_CHARSET_UTF8, result);
    }
}
