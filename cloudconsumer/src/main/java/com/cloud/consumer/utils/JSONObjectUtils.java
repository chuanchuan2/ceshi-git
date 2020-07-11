package com.cloud.consumer.utils;

import com.cloud.consumer.common.JsonBigDecimalValueProcessor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.math.BigDecimal;

//后续json组件在这里进行扩展
public class JSONObjectUtils {
    //json转换
    public static String formObjectForBigDecimal(Object object){
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.registerJsonValueProcessor(BigDecimal.class,new JsonBigDecimalValueProcessor());
        return JSONObject.fromObject(object,jsonConfig).toString();
    }

    //json转换
    public static String formArrayForBigDecimal(Object object){
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.registerJsonValueProcessor(BigDecimal.class,new JsonBigDecimalValueProcessor());
        return JSONArray.fromObject(object,jsonConfig).toString();
    }
}
