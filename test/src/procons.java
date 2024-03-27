import java.util.concurrent.Semaphore;

/**
 * @ClassName procons
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2024/3/16 9:15
 * @Version
 */

public class procons {
    public static Semaphore apple = new Semaphore(0);
    public static Semaphore orange = new Semaphore(0);
    public static Semaphore plate = new Semaphore(1);

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    plate.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("father, put an orange");
                orange.release();
            }
        }, "father").start();
        new Thread(() -> {
            while (true) {
                try {
                    plate.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("mother, put an apple");
                apple.release();
            }
        }, "mother").start();
        new Thread(() -> {
            while (true) {
                try {
                    orange.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("son, eat an orange");
                plate.release();
            }
        }, "son").start();
        new Thread(() -> {
            while (true) {
                try {
                    apple.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("daughter, eat an apple");
                plate.release();
            }
        }, "daughter").start();
    }
}
