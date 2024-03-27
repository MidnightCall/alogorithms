import java.lang.annotation.Target;

/**
 * @ClassName RobotWalk
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/8 10:03
 * @Version
 */

public class RobotWalk {
    public static int ways1(int N, int S, int K, int E){
        return process(N, S, K, E);
    }

    public static int process(int N, int curr, int rest, int target){
        if(rest == 0){
            return curr == target ? 1 : 0;
        }
        if(curr == 1){
            return process(N, curr + 1, rest - 1, target);
        }
        if(curr == N){
            return process(N, curr - 1, rest - 1, target);
        }
        return process(N, curr + 1, rest - 1, target) +
                process(N, curr - 1, rest - 1, target);
    }

    public static int ways2(int N, int curr, int rest, int target) {
        int row = N + 1;
        int col = rest + 1;
        int dp[][] = new int[row][col];
        dp[target][0] = 1;
        for(int j = 1; j < col; ++j){
            for(int i = 1; i < row; ++i){
                if(i == 1){
                    dp[i][j] = dp[i + 1][j - 1];
                }else if(i == N){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = dp[i + 1][j - 1] + dp[i - 1][j - 1];
                }
            }
        }

        return dp[curr][rest];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 4));
        System.out.println(ways2(5, 2, 4, 4));
        System.out.println(ways1(7,4,9,5));
        System.out.println(ways2(7,4,9,5));
//        System.out.println(ways3(7, 4, 9, 5));
    }
}
