//package com.example.mytest2023.helper;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by 钟文祥 on 2023/9/4.
// * Describer: 线程池
// */
//public class ThreadPoolHelper {
//
//    public void init() {
//        //配置核心参数
//        Executor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
//                TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
//        // 构造函数源码分析public
//        // ThreadPoolExecutor (int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit
//        // unit,BlockingQueue<Runnable workQueue>,ThreadFactory threadFactory )
//
//        // 注：在Java中，已内置4种常见线程池，下面会详细说明
//        // 2. 向线程池提交任务：execute（）// 说明：传入 Runnable对象threadPool.execute(new Runnable()
//        // {@Overridepublic void run() {... // 线程执行任务}});
//        // 3. 关闭线程池shutdown() threadPool.shutdown();
//        // 关闭线程的原理 a. 遍历线程池中的所有工作线程 b. 逐个调用线程的interrupt（）中断线程（注：无法响应中断的任务可能永远无法终止）
//        // 也可调用shutdownNow（）关闭线程：threadPool.shutdownNow（）
//        // 二者区别：
//        // shutdown：   设置 线程池的状态 为 SHUTDOWN，然后中断所有没有正在执行任务的线程
//        // shutdownNow：设置 线程池的状态 为 STOP，然后尝试停止所有的正在执行或暂停任务的线程，并返回等待执行任务的列表//
//        // 使用建议：一般调用shutdown（）关闭线程池；若任务不一定要执行完，则调用shutdownNow（）
//
//    }
//}
