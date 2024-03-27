package com.kojikoji.sort;

/**
 * @ClassName NumComparator
 * @Description 用于测试的对数器
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/5 21:23
 * @Version
 */

public class NumComparator {
    public static int[] generateArray(int maxSize, int maxNumber){
        // 产生数组随机长度[1, maxSize]
        int length = (int) (Math.random()*maxSize + 1);
        int[] arr = new int[length];

        // 数组元素赋随机值[1, maxNumber]
        for(int i = 0; i < length; i++){
            arr[i] = (int) (Math.random() * maxNumber + 1);
        }

        return arr;
    }

    public static int[] copyArray(int[] array){
        int length = array.length;
        int[] tempArray = new int[length];
        for(int i = 0; i < length; i++){
            tempArray[i] = array[i];
        }
        return tempArray;
    }

    public static boolean comparator(int[] array1, int[] array2){
        if(array1.length != array2.length)
            return false;
        for(int i = 0; i < array1.length; i++){
            if(array1[i] != array2[i]){
                return false;
            }
        }
        return true;
    }
}
