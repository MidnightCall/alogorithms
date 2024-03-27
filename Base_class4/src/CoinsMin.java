import org.junit.Test;

/**
 * @ClassName CoinsMin
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/7 20:21
 * @Version
 */

public class CoinsMin {
    public int getMin(int[] coins, int target){
        return process(coins, target, 0, 0);
    }

    public int process(int[] coins, int target, int i, int sum){
        if(i == coins.length){
            return sum == target ? 0 : coins.length + 1;
        }
        return Math.min(process(coins, target, i + 1, sum + coins[i]) + 1,
                        process(coins, target, i + 1, sum));
    }

    @Test
    public void test(){
        int[] coins = {2, 7, 3, 5, 3};
        int target = 10;
        System.out.println(getMin(coins, target));
    }
}
