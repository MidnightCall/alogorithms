import org.junit.Test;

/**
 * @ClassName BobDie
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/8 20:37
 * @Version
 */

public class BobDie {
    public double bob1(int N, int M, int i, int j, int k){
        long liveCount = process(N, M, i, j, k);
        long allCount = (long) Math.pow(4, k);
        double rate = 1.0 * liveCount / allCount;
        return rate;
    }

    public long process(int N, int M, int i, int j, int rest){
        if(i < 0 || j < 0 || i >= N || j >= M){
            return 0;
        }
        if(rest == 0){
            return 1;
        }
        long liveCount = process(N, M, i + 1, j, rest - 1);
        liveCount += process(N, M, i - 1, j, rest - 1);
        liveCount += process(N, M, i, j + 1, rest - 1);
        liveCount += process(N, M, i, j - 1, rest - 1);

        return liveCount;
    }

    public double bob2(int N, int M, int i, int j, int k){
        long[][][] dp = new long[N][M][k + 1];
        // 初始化基准条件
        for(int c = 0; c < N; ++c){
            for(int r = 0; r < M; ++r){
                dp[c][r][0] = 1;
            }
        }
        for(int h = 1; h <= k; ++h){
            for(int c = 0; c < N; ++c){
                for(int r = 0; r < M; ++r){
                    dp[c][r][h] += getValue(dp, N, M, c + 1, r, h - 1);
                    dp[c][r][h] += getValue(dp, N, M, c - 1, r, h - 1);
                    dp[c][r][h] += getValue(dp, N, M, c, r + 1, h - 1);
                    dp[c][r][h] += getValue(dp, N, M, c, r - 1, h - 1);
                }
            }
        }

        long liveCount = dp[i][j][k];
        long allCount = (long)Math.pow(4, k);

        return 1.0 * liveCount / allCount;
    }

    public long getValue(long[][][] dp, int N, int M, int i, int j, int k){
        if(i < 0 || j < 0 || i >= N || j >= M){
            return 0;
        }
        return dp[i][j][k];
    }

    @Test
    public void test(){
        int N = 10;
        int M = 10;
        int i = 3;
        int j = 2;
        int K = 5;
        System.out.println(bob1(N, M, i, j, K));
        double rate = bob2(N, M, i, j, K);
        System.out.println(bob2(N, M, i, j, K));
    }
}
