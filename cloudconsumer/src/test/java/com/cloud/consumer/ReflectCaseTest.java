package com.cloud.consumer;

import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

@SpringBootTest
public class ReflectCaseTest {
    public static void main(String[] args) throws Exception {
        Proxy target = new Proxy();
        Method run = target.getClass().getDeclaredMethod("run");
        run.invoke(target);

    }

    static class Proxy {
        public void run() {
            System.out.println("run");
        }
    }

}
