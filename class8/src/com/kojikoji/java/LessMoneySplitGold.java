package com.kojikoji.java;

import java.util.PriorityQueue;

/**
 * @ClassName LessMoneySplitGold
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/12/29 16:39
 * @Version
 */

public class LessMoneySplitGold {
    public static int lessMoney(int[] arr){
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int num : arr){
            pq.add(num);
        }

        int sum = 0;
        int curr = 0;
        while(pq.size() > 1){
            curr = pq.poll() + pq.poll();
            sum += curr;
            pq.add(curr);
        }

        return sum;
    }
}
