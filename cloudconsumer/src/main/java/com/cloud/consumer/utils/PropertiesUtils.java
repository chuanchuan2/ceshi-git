package com.cloud.consumer.utils;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {
    //缓存
    private static Map<String , Properties> propertiesMap=new HashMap<>();
    //清除缓存
    private static void clearConfigProperties(){
        PropertiesUtils.propertiesMap.clear();
    }
    //根据文件的key获取文件的值
     /*this.getClass().getClassLoader().getResourceAsStream("aaa.properties")
        和 Thread.currentThread().getContextClassLoader("aaa.properties")
        一个是全局的，只要这个类被加载，你的资源就会被加载。
        一个是局部的，被发起一个线程，就加载一次资源，而且只能在本线程中用。具体内存中加载了多少份，就不清楚了，
        可能是一份，也可以是n份*/
    public static String getValue(String fileName,String key){
        if (StringUtils.isBlank(fileName)){
            return null;
        }
        if (!propertiesMap.containsKey(fileName)){
            synchronized (PropertiesUtils.class){
                Properties properties=new Properties();
                InputStream is=null;
                try {
                    is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
                    properties.load(is);
                    propertiesMap.put(fileName,properties);
                } catch (IOException e) {
                    //文件不存在异常
                    e.printStackTrace();
                    return null;
                }finally {
                    //关闭流
                    if (is!=null){
                        try{
                           is.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return propertiesMap.get(fileName).getProperty(key);
    }
}
