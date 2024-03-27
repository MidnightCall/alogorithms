import org.junit.Test;

import java.security.PublicKey;

/**
 * @ClassName HorseJump
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/8 17:34
 * @Version
 */

public class HorseJump {
    public int count1(int x, int y, int step){
        return process(x, y, step);
    }

    public int process(int x, int y, int step){
        if(x < 0 || x > 8 || y < 0 || y > 9){
            return 0;
        }
        if(step == 0){
            return (x == 0 && y == 0) ? 1 : 0;
        }

        return process(x - 1, y + 2, step - 1)
                + process(x + 1, y + 2, step - 1)
                + process(x + 2, y + 1, step - 1)
                + process(x + 2, y - 1, step - 1)
                + process(x + 1, y - 2, step - 1)
                + process(x - 1, y - 2, step - 1)
                + process(x - 2, y - 1, step - 1)
                + process(x - 2, y + 1, step - 1);
    }

    public int count2(int x, int y, int step){
        if(x < 0 || x > 8 || y < 0 || y > 9 || step < 0){
            return 0;
        }
        int[][][] dp = new int[9][10][step + 1];
        dp[0][0][0] = 1;
        for(int k = 1; k <= step; ++k){
            for(int i = 0; i < 9; ++i){
                for(int j = 0; j < 10; ++j){
                    dp[i][j][k] += getValue(dp, i - 1, j + 2, k - 1);
                    dp[i][j][k] += getValue(dp, i + 1, j + 2, k - 1);
                    dp[i][j][k] += getValue(dp, i + 2, j + 1, k - 1);
                    dp[i][j][k] += getValue(dp, i + 2, j - 1, k - 1);
                    dp[i][j][k] += getValue(dp, i + 1, j - 2, k - 1);
                    dp[i][j][k] += getValue(dp, i - 1, j - 2, k - 1);
                    dp[i][j][k] += getValue(dp, i - 2, j - 1, k - 1);
                    dp[i][j][k] += getValue(dp, i - 2, j + 1, k - 1);
                }
            }
        }

        return dp[x][y][step];
    }

    public int getValue(int[][][] dp, int x, int y, int z){
        if(x < 0 || x > 8 || y < 0 || y > 9){
            return 0;
        }
        return dp[x][y][z];
    }


    @Test
    public void test(){
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(count1(x, y, step));
        System.out.println(count2(x, y, step));
    }

}
