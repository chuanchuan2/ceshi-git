package com.cloud.consumer.proxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyDog implements InvocationHandler {

    // 目标类，也就是被代理对象
    private TaiDi taiDi;

    public ProxyDog(TaiDi taiDi) {
        this.taiDi = taiDi;
    }

    //实现InvocationHandler接口重写invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(taiDi, args);
        return invoke ;
    }
}
