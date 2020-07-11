package com.cloud.consumer.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class TestNotify implements Runnable {

    private Integer num;

    public TestNotify(Integer num) {
        this.num = num;
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("开始下载图片");
            if(num>0){
                num=num-10;
                System.out.println("正在下载图片" +num+ "%," + Thread.currentThread().getName());
                try {
                    num.wait();
                    System.out.println("图片下载等待！" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("图片下载成功！" + Thread.currentThread().getName());
        }
    }
}
