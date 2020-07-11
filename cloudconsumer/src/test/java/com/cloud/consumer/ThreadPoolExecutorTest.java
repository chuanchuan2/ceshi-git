package com.cloud.consumer;

import com.cloud.consumer.pojo.Result;
import com.cloud.consumer.thread.MyReject;
import com.cloud.consumer.thread.ThreadTaskCallable;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

@SpringBootTest
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        //ThreadPoolExecutor是线程池ExecutorService的实现类
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(
                //核心线程数
                1,
                //最大线程数
                5,
                //线程多久时间没有执行任务终止线程
                600000,
                //时间单位
                TimeUnit.SECONDS,
                //指定一种队列
           /* 无界队列
            队列大小无限制，常用的为无界的LinkedBlockingQueue，使用该队列做为阻塞队列时要尤其当心，
            当任务耗时较长时可能会导致大量新任务在队列中堆积最终导致OOM。阅读代码发现，Executors.newFixedThreadPool
            采用就是 LinkedBlockingQueue，而楼主踩到的就是这个坑，当QPS很高，发送数据很大，大量的任务被添加到这个
            无界LinkedBlockingQueue 中，导致cpu和内存飙升服务器挂掉*/

            /*有界队列
            常用的有两类，一类是遵循FIFO原则的队列如ArrayBlockingQueue，另一类是优先级队列如PriorityBlockingQueue。
            PriorityBlockingQueue中的优先级由任务的Comparator决定。使用有界队列时队列大小需和线程池大小互相配合，
            线程池较小有界队列较大时可减少内存消耗，降低cpu使用率和上下文切换，但是可能会限制系统吞吐量*/

            /*同步移交队列
            如果不希望任务在队列中等待而是希望将任务直接移交给工作线程，可使用SynchronousQueue作为等待队列。
            SynchronousQueue不是一个真正的队列，而是一种线程之间移交的机制。要将一个元素放入SynchronousQueue中，
            必须有另一个线程正在等待接收这个元素。只有在使用无界线程池或者有饱和策略时才建议使用该队列*/
                new ArrayBlockingQueue<>(15),
                //大于最大线程池执行拒绝策略
                new MyReject()
        );

        ThreadTaskCallable threadTaskCallable = new ThreadTaskCallable(20);

        for (int i = 0; i < 20; i++) {
            FutureTask futureTask=new FutureTask(threadTaskCallable);
            Future<Result> future = (Future<Result>) threadPoolExecutor.submit(futureTask);
            try {
                //Thread.sleep(1000);
                //future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Thread thread=new Thread(futureTask);
            //thread.start();
        }


    }
}
