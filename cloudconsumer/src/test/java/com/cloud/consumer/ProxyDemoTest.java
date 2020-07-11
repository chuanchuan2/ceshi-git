package com.cloud.consumer;

import com.cloud.consumer.proxyDemo.Dog;
import com.cloud.consumer.proxyDemo.ProxyDog;
import com.cloud.consumer.proxyDemo.TaiDi;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;

@SpringBootTest
public class ProxyDemoTest {

    public static void main(String[] args) {
        TaiDi taiDi=new TaiDi();
        ProxyDog proxyDog=new ProxyDog(taiDi);
        //参数：1.代理类的类加载器，2.被代理类的接口如果有多个就是数组形式传入，3.代理类的实例
        Dog dog = (Dog) Proxy.newProxyInstance(proxyDog.getClass().getClassLoader(), taiDi.getClass().getInterfaces(), proxyDog);
        String s = dog.eat("泰迪");
        System.out.println(s);
    }
}
