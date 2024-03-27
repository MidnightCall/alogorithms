import com.sun.source.tree.Tree;
import org.junit.Test;

import javax.swing.tree.TreeNode;
import java.security.CryptoPrimitive;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

/**
 * @ClassName Test
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/12/29 19:39
 * @Version
 */

public class TestSpace {
    public int count = 0;

    @Test
    public void testSync() throws InterruptedException {

        new Thread(() -> {
            while (true) {
                System.out.println("t1 -> " + count);
                ++count;

            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println("t2 -> " + count);
                ++count;
            }
        });

        new Thread(() -> {
            while (true) {
                System.out.println("t3 -> " + count);
                ++count;
            }
        }).start();

        Thread.sleep(10000);
    }
    @Test
    public void test(){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        Queue<Integer> queue = new LinkedList<>(stack);
        for (Integer integer : queue) {
            System.out.println(integer);
        }
    }

}
