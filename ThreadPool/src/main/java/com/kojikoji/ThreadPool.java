package com.kojikoji;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPool
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/3/13 10:33
 * @Version
 */

public class ThreadPool {

    private BlockingQueue<Runnable> taskQueue;

    private HashSet<Worker> workers = new HashSet<Worker>();

    private int coreSize;

    private long timeout;

    private TimeUnit unit;

    public ThreadPool() {
    }

    public ThreadPool(int coreSize, long timeout, TimeUnit unit) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.unit = unit;
        taskQueue = new BlockingQueue<Runnable>();
    }

    public void execute(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else {
                taskQueue.put(task);
            }
        }
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            while (task != null || (task = taskQueue.take()) != null) {
                try {
                    task.run();
                } catch (Exception e)  {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (workers) {
                workers.remove(this);
            }
        }
    }

}
