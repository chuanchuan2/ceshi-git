package com.cloud.consumer.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolFactory {

    /*一、线程池： 提供一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁的额外开销，提高了响应的速度。
    二、线程池的体系结构：
    java.util.concurrent.Executor 负责线程的使用和调度的根接口
    |–ExecutorService 子接口： 线程池的主要接口
    |–ThreadPoolExecutor 线程池的实现类
    |–ScheduledExceutorService 子接口： 负责线程的调度
    |–ScheduledThreadPoolExecutor : 继承ThreadPoolExecutor，实现了ScheduledExecutorService
    三、工具类 ： Executors
    ExecutorService newFixedThreadPool() : 创建固定大小的线程池
    ExecutorService newCachedThreadPool() : 缓存线程池，线程池的数量不固定，可以根据需求自动的更改数量。
    ExecutorService newSingleThreadExecutor() : 创建单个线程池。 线程池中只有一个线程
    ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程，可以延迟或定时的执行任务
*/

}
