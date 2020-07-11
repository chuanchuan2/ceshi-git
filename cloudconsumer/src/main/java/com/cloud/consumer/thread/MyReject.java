package com.cloud.consumer.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
//线程池的拒绝策略
public class MyReject implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(Thread.currentThread().getName()+"拒绝le");
        //进入拒绝策略后如想继续执行任务，r.run()的方式启一个main主线程完成任务
        r.run();
    }
}
