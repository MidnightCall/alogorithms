package com.kojikoji.java;

/**
 * @ClassName Knapsack
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/1 16:38
 * @Version
 */

public class Knapsack {
    public static int maxValue1(int[] weights, int[] values, int bag) {
        return process(weights, values, 0, 0, bag);
    }

    public static int process(int[] weights, int[] values, int i, int alreadyWeight, int bag){
        if(alreadyWeight > bag){
            return 0;
        }
        if(i == weights.length){
            return 0;
        }
        return Math.max(
                process(weights, values, i + 1, alreadyWeight, bag),
                values[i] + process(weights, values, i + 1, alreadyWeight + weights[i], bag)
        );
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7 };
        int[] values = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue1(weights, values, bag));
//        System.out.println(maxValue2(weights, values, bag));
    }

}
