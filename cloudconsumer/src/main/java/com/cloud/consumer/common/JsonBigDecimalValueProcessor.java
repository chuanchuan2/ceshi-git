package com.cloud.consumer.common;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonBigDecimalValueProcessor implements JsonValueProcessor {
    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    @Override
    public Object processObjectValue(String s, Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    private Object process(Object value){
        return value==null?"":value.toString();
    }
}
