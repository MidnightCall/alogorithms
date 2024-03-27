import org.junit.Test;

/**
 * @ClassName CardsInLine
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/8 16:20
 * @Version
 */

public class CardsInLine {
    public boolean win1(int[] nums){
        int sum = 0;
        for(int i = 0; i < nums.length; ++i){
            sum += nums[i];
        }
        int value = f(nums, 0, nums.length - 1);
        return value > (sum >> 1);
    }

    public int f(int[] nums, int i, int j){
        if(i == j){
            return nums[i];
        }
        return Math.max(nums[i] + s(nums, i + 1, j),
                        nums[j] + s(nums, i, j - 1));
    }

    public int s(int[] nums, int i, int j){
        if(i == j){
            return 0;
        }
        return Math.min(f(nums, i + 1, j), f(nums, i, j - 1));
    }

    public boolean win2(int[] nums){
        int sum = 0;
        int n = nums.length;
        for(int i = 0; i < n; ++i){
            sum += nums[i];
        }
        int[][] fdp = new int[n][n];
        int[][] sdp = new int[n][n];
        // 基准值
        for(int j = 0; j < n; ++j){
            fdp[j][j] = nums[j];
            for(int i = j - 1; i >= 0; --i){
                fdp[i][j] = Math.max(nums[i] + sdp[i + 1][j],
                                    nums[j] + sdp[i][j - 1]);
                sdp[i][j] = Math.min(fdp[i + 1][j], fdp[i][j - 1]);
            }
        }
        int value = fdp[0][n - 1];
        return value > (sum >> 1);
    }

    @Test
    public void test(){
        int[] nums = {1,5,233,7};
        System.out.println(win1(nums));
        System.out.println(win2(nums));
    }

}
