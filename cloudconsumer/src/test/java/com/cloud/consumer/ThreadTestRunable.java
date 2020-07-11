package com.cloud.consumer;

import com.cloud.consumer.thread.ThreadTaskRunable;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThreadTestRunable {
    public static void main(String[] args) {
        ThreadTaskRunable task=new ThreadTaskRunable(50);
        for (int i = 0; i < 100; i++) {
            Thread thread=new Thread(task);
            thread.setName("电商"+i);
            thread.start();
        }
    }
}
