import java.awt.*;
import java.io.ObjectStreamException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Main {
    private static int num = 0;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition[] conditions = new Condition[3];

    static {
        for (int i = 0; i < conditions.length; ++i) {
            conditions[i] = lock.newCondition();
        }
    }

    public static void printNum(int targetNum) {
        while (true) {
            lock.lock();
            try {
                while (num % 3 != targetNum) {
                    try {
                        conditions[targetNum].await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(Thread.currentThread().getName() + " : " + (targetNum + 1));
                ++num;
                conditions[(targetNum+1)%3].signal();
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println(getInt());
    }
    public static int getInt() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }
}