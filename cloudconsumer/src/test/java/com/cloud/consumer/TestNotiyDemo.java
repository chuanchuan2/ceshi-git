package com.cloud.consumer;

import com.cloud.consumer.proxyDemo.Dog;
import com.cloud.consumer.proxyDemo.ProxyDog;
import com.cloud.consumer.proxyDemo.TaiDi;
import com.cloud.consumer.thread.TestNotify;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;

@SpringBootTest
public class TestNotiyDemo {

    public static void main(String[] args) {
        Integer num=100;
        TestNotify testNotify=new TestNotify(num);
        for (int i = 0; i <20 ; i++) {
            Thread thread=new Thread(testNotify);
            thread.setName("线程"+i);
            thread.start();
        }
       /* synchronized (testNotify){
            testNotify.notify();
        }*/
    }
}
