import java.util.concurrent.Semaphore;

/**
 * @ClassName Sem
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/3/14 19:56
 * @Version
 */

public class Sem {
    private static Semaphore[] semaphores = new Semaphore[3];
    private static int num = 1;

    static {
        semaphores[0] = new Semaphore(1);
        for (int i = 1; i < semaphores.length; ++i) {
            semaphores[i] = new Semaphore(0);
        }
    }

    private static void print(int target) {
        while (true) {
            try {
                semaphores[target].acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " : " + num);
            ++num;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            semaphores[(target + 1) % 3].release();
        }

    }

    public static void main(String[] args) {
        new Thread(()->print(0), "t1").start();
        new Thread(()->print(1), "t2").start();
        new Thread(()->print(2), "t3").start();
    }
}
