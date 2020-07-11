package com.cloud.consumer.thread;

//不带返回值的多线程测试实现Runnable接口
public class ThreadTaskRunable implements Runnable {

    private int store;

    public ThreadTaskRunable(int store){
        this.store=store;
    }

    //重写run方法
    @Override
    public void run() {
        //如果商品ID大于0可以接着卖
        synchronized (this){
            if (store > 0) {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"：卖出了商品-->剩余商品"+store--+"个");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
