package com.kojikoji.java;

import org.junit.Test;

import java.lang.annotation.Inherited;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * @ClassName NetherlandFlag
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/3/18 15:44
 * @Version
 */

public class NetherlandFlag {

    // 基础快排partition
    public int[] partition1(int[] nums, int flag){
        if(nums == null || nums.length < 2){
            return nums;
        }
        int less = -1;
        int i = 0;
        while(i < nums.length){
            if(nums[i] < flag){
                swap(nums, ++less, i++);
            }else{
                i++;
            }
        }
        return nums;
    }

    public int[] partition2(int[] nums, int flag){
        if(nums == null || nums.length < 2){
            return nums;
        }

        int less = -1;
        int more = nums.length;
        int L = 0;
        while(L < more){
            if(nums[L] < flag){
                swap(nums, ++less, L++);
            }else if(nums[L] == flag){
                ++L;
            }else{
                swap(nums, --more, L);
            }
        }

        return nums;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test(){
        int[] nums = new int[]{3,1,2,5,4,6,7,4,5,3,6,9,5};
        int[] res = partition2(nums, 5);
        for (int re : res) {
            System.out.print(re + " ");
        }
    }
}
