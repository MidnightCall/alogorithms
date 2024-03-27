package com.kojikoji;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BlockingQueue
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/3/13 10:33
 * @Version
 */

class BlockingQueue<T> {
    private Deque<T> queue = new ArrayDeque<>();

    private ReentrantLock lock = new ReentrantLock();

    private Condition fullWaitSet = lock.newCondition();

    private Condition emptyWaitSet = lock.newCondition();

    private int capacity;

    public T takeWithTimeout(long time, TimeUnit unit) {
        lock.tryLock();
        try {
            long nanos = unit.toNanos(time);
            while (queue.isEmpty()) {
                try {
                    if (nanos <= 0) {
                        return null;
                    }
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            fullWaitSet.signal();
            return queue.removeFirst();
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        lock.tryLock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T element = queue.removeFirst();
            fullWaitSet.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }

    public void put(T element) {
        lock.lock();

        try {
            while (queue.size() == capacity) {
                try {
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.addLast(element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    public void putWithTimeout(T element, long time, TimeUnit unit) {
        lock.lock();
        try {
            long nanos = unit.toNanos(time);
            while (queue.size() == capacity) {
                try {
                    if (nanos <= 0) {
                        return;
                    }
                    nanos = fullWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            queue.addLast(element);
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    public int getSize() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }

    }
}