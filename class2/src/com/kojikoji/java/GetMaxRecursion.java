package com.kojikoji.java;

/**
 * @ClassName GetMaxRecursion
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/3/18 11:10
 * @Version
 */

public class GetMaxRecursion {

    public int getMax(int[] arr){
        return process(arr, 0, arr.length - 1);
    }

    public int process(int[] arr, int L, int R){
        if(L == R){
            return arr[L];
        }
        int mid = ((R-L) >> 1) + L;
        int left = process(arr, L, mid);
        int right = process(arr, mid + 1, R);
        return Math.max(left, right);
    }
}
