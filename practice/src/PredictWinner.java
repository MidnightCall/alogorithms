import org.junit.Test;

/**
 * @ClassName PredictWinner
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/8 17:03
 * @Version
 */

public class PredictWinner {
    public boolean win1(int[] nums){
        return process(nums, 0, nums.length - 1, 1) >= 0;
    }

    public int process(int[] nums, int i, int j, int turn){
        if(i == j){
            return nums[i] * turn;
        }
        return Math.max(nums[i] * turn + process(nums, i + 1, j, -turn),
                        nums[j] * turn + process(nums, i, j - 1, -turn));
    }

    public boolean win2(int[] nums){
        int n = nums.length;
        int dp[][] = new int[n][n];
        for(int j = 0; j < n; ++j){
            dp[j][j] = nums[j];
            for(int i = j - 1; i >= 0; --i){
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j],
                                    nums[j] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1] >= 0;
    }

    @Test
    public void test(){
        int[] nums = {1,5,233,7};
        System.out.println(win1(nums));
        System.out.println(win2(nums));
    }

}
