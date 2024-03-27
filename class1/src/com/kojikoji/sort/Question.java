package com.kojikoji.sort;

import org.junit.Test;

import java.util.HashMap;

/**
 * @ClassName Question
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/6 9:36
 * @Version
 */

public class Question {
    @Test
    public void test1(){
        // 已知数组中只有一个元素有奇数个，其他有偶数个，求奇数个的元素
        int arr[] = new int[]{1,2,1,3,5,2,6,6,8,9,9,3,8};
        // 解法1: HashMap
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            if(hashMap.containsKey(arr[i])){
                hashMap.put(arr[i], hashMap.get(arr[i]) + 1);
            }else {
                hashMap.put(arr[i], 1);
            }
        }
        for(int i : hashMap.keySet()){
            if(hashMap.get(i) % 2 != 0){
                System.out.println(i);
            }
        }

        // 解法2:利用异或的特性
        int eor = 0;
        for(int i = 0; i < arr.length; i++){
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    @Test
    public void test2(){
        // 已知一个数组中只有两个数字是奇数个，剩余均为偶数个，求这两个数字
        int[] arr = new int[]{1,2,1,3,5,2,6,6,8,9,9,3,8,8};
        int eor = 0;
        int eor1 = 0;
        for(int i = 0; i < arr.length; i++){
            eor ^= arr[i];
        }
        int rightOne = eor & (~eor + 1);
        for(int i = 0; i < arr.length; i++){
            if((arr[i] & rightOne) != 0){
                eor1 ^= arr[i];
            }
        }

        System.out.println(eor1 + "和" + (eor1 ^ eor));

    }

    @Test
    public void test3(){
        HashMap<Integer, Integer> hashtable = new HashMap<>();
        hashtable.put(1, 5);
        hashtable.put(1, 6);
        System.out.println(hashtable);
    }
}
