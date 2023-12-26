package com.example.mytest2023.helper;

import android.util.Log;

/**
 * Created by 钟文祥 on 2018/11/19.
 * Describer:
 */
public abstract class BaseThread extends Thread {
    private final Object lock = new Object();
    protected boolean pause = false;

    private ThreadState threadState = ThreadState.None;

    public enum ThreadState {
        None(0), Running(1), Pause(2);
        private int value;

        public int getValue() {
            return value;
        }

        ThreadState(int value) {
            this.value = value;
        }
    }

    public ThreadState getThreadState() {
        return threadState;
    }

    @Override
    public synchronized void start() {
        super.start();
        threadState = ThreadState.Running;
    }

    /** 调用这个方法实现暂停线程 */
    public void pauseThread() {
        pause = true;
        threadState = ThreadState.Pause;
    }

    /** 调用这个方法实现恢复线程的运行 */
    public void resumeThread() {
        pause = false;
        threadState = ThreadState.Running;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public void stopThread() {
        if (isAlive()) {
            pauseThread();
            interrupt();
            threadState = ThreadState.None;
        }
    }


    /**
     * 注意：这个方法只能在run方法里调用，不然会阻塞主线程，导致页面无响应
     */
    public void onPause() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        super.run();

        try {
            while (true) {
                // 让线程处于暂停等待状态
                while (pause) {
                    onPause();
                }
                try {
                    threadState = ThreadState.Running;
                    Thread.sleep(onSleepTime());
                    onRun();
                } catch (InterruptedException e) {
                    //捕获到异常之后，执行break跳出循环
                    Log.e("ggg", "run: 捕获到异常之后，执行break跳出循环");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void onRun();

    public abstract int onSleepTime();

}
