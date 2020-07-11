package com.cloud.consumer;

import com.cloud.consumer.thread.ThreadTaskCallable;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.FutureTask;

@SpringBootTest
public class ThreadTestCallable {

    public static void main(String[] args) {
        ThreadTaskCallable task=new ThreadTaskCallable(50);
        for (int i = 0; i < 100; i++) {
            FutureTask futureTask=new FutureTask(task);
            Thread thread=new Thread(futureTask);
            thread.start();
        }
    }
}
