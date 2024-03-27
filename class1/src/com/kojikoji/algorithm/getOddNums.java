package com.kojikoji.algorithm;

import org.junit.Test;

/**
 * @ClassName getOddNums
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/3/18 10:59
 * @Version
 */

public class getOddNums {

    // 出现一次的数字
    public int getOddsNum(int[] nums){
        int xor = 0;
        for(int num : nums){
            xor ^= num;
        }

        return xor;
    }

    // 两个出现一次的数字
    public int[] getOddsNum2(int[] nums){
        int xor = 0;
        for(int num : nums){ // 获取a ^ b
            xor ^= num;
        }
        int rightOne = xor & (~xor + 1);
        int eor = 0;
        for(int num : nums){
            if((num & rightOne) != 0) {
                eor ^= num;
            }
        }
        return new int[]{eor, eor ^ xor};
    }


    @Test
    public void test(){
        int[] arr = new int[]{1,2,3,4,4,3,3,2 };
        int[] res = getOddsNum2(arr);
        for (int re : res) {
            System.out.println(re);
        }
    }
}
