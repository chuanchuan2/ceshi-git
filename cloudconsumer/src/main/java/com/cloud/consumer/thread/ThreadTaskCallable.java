package com.cloud.consumer.thread;


import com.cloud.consumer.pojo.Result;

import java.util.concurrent.Callable;

//带返回值的多线程测试实现Callable接口
public class ThreadTaskCallable implements Callable {

    private int store;

    public ThreadTaskCallable(int store){
        this.store=store;
    }

    //重写call方法
    @Override
    public Result call() throws Exception {
        //如果商品ID大于0可以接着卖
        Result result=new Result();
        synchronized (this){
            if (store > 0) {
                //Thread.sleep(1000);
                result.setCode("1");
                result.setMsg("卖了");
                System.out.println(Thread.currentThread().getName()+"：卖出了商品-->剩余商品"+store--+"个");
            }
        }

        return result;
    }
}
