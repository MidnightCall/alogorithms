package com.kojikoji.sort;

import org.junit.Test;

import javax.swing.text.StyleContext;
import javax.swing.text.html.HTMLEditorKit;
import java.util.Arrays;
import java.util.logging.Level;

/**
 * @ClassName SelectionSort
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/5 20:56
 * @Version
 */

public class Sort {
    //选择排序的实现
    public void selectionSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; ++j) {
                min = arr[j] < arr[min] ? j : min;
            }
            swap(arr, min, i);
        }
    }

    // 冒泡排序的实现
    public void bubbleSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = arr.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // 归并排序的实现
    // R: 待排序数组最右边元素的下标（注意不是元素数量）
    public void mergeSort(int[] arr, int L, int R){
        if (L == R) {
            return;
        }
        int M = L + ((R-L) >> 1);
        mergeSort(arr, L, M);
        mergeSort(arr, M + 1, R);
        merge(arr, L, M, R);
    }

    public void merge(int[] arr, int L, int M, int R) {
        int[] helper = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            helper[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            helper[i++] = arr[p1++];
        }
        while (p2 <= R) {
            helper[i++] = arr[p2++];
        }
        System.arraycopy(helper, 0, arr, L, helper.length);
    }

    // 插入排序的实现
    public void insertionSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = 1; i < arr.length; ++i) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j+1]; --j) {
                swap(arr, j, j + 1);
            }
        }
    }

    // 希尔排序的实现
    public void shellSort(int[] arr){
        if(arr == null || arr.length < 2)
            return;
        for(int d = arr.length / 2; d >= 1; d = d/2){
            for(int i = d; i < arr.length; ++i){
                for(int j = i; j >= d && arr[j] < arr[j - d]; j -= d){
                    swap(arr, j, j-d);
                }
            }
        }

    }

    public void baseQuickSort(int[] arr) {
        baseQuick(arr, 0, arr.length - 1);
    }

    // 快速排序的基础实现
    public void baseQuick(int[] arr, int L, int R){
        if(arr == null || arr.length == 0) {
            return;
        }
        if (L < R) {
            int pivot = basePartition(arr, L, R);
            baseQuick(arr, L, pivot-1);
            baseQuick(arr, pivot+1, R);
        }
    }

    public int basePartition(int[] arr, int L, int R) {
        int less = L - 1;
        while (L < R) {
            if (arr[L] < arr[R]) {
                swap(arr, ++less, L++);
            } else {
                ++L;
            }
        }
        swap(arr, ++less, R);
        return less;
    }

    // 优化快速排序的实现
    public void quickSort(int[] arr) {
        quick(arr, 0, arr.length-1);
    }

    public void quick(int[] arr, int L, int R) {
        if(L < R) {
            swap(arr, R, L + (int) (Math.random() * (R-L+1)));
            int[] pivot = partition(arr, L, R);
            quick(arr, L, pivot[0] - 1);
            quick(arr, pivot[1] + 1, R);
        }
    }

    public int[] partition(int[] arr, int L, int R){
        int less = L - 1;
        int more = R;
        while(L < more) {
            if(arr[L] < arr[R]) {
                swap(arr, ++less, L++);
            } else if(arr[L] > arr[R]){
                swap(arr, --more, L);
            } else {
                ++L;
            }
        }
        swap(arr, more, R);
        return new int[]{++less, more};
    }

    // 堆排序
    public void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        // 制造大根堆
        // 方法一：略慢，从头至尾做heapInsert操作
        /*
        for(int i = 0; i < arr.length; i++){
            heapInsert(arr, i);
        }
         */
        // 方法二：略快，从树叶开始依次向上做heapify操作
        for(int i = (arr.length >> 1); i >= 0; i--){
            heapify(arr, i, arr.length);
        }

        // 将最大的根节点依次与末尾的节点交换， 并对换上来的节点做heapify操作
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while(heapSize > 0){
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    // 处于index位置的数向上移动
    public void heapInsert(int[] arr, int index){
        while(arr[index] > arr[(index - 1) >> 1]){
            swap(arr, index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }
    }

    // 指定节点向下移动
    public void heapify(int[] arr, int index, int heapSize){
        int left = (index << 1) + 1;
        while(left < heapSize){ // 下方还有叶子
            // 左右子节点取大值
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1]?
                            left + 1 : left;
            // 父节点和子节点取大值
            largest = arr[largest] > arr[index] ? largest : index;
            // 父节点为最大值，退出循环
            if(largest == index){
                break;
            }
            // 父节点不为最大值，与最大值节点交换，继续循环
            swap(arr, index, largest);
            index = largest;
            left = (largest << 1) + 1;
        }
    }

    // 计数排序
    public void countSort(int[] arr){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int num : arr) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }

        int[] count = new int[max - min + 1];
        for(int num : arr) {
            ++count[num - min];
        }
        int i = 0;
        for(int num = min; num <= max; ++num) {
            while(count[num-min] > 0) {
                arr[i++] = num;
                --count[num-min];
            }
        }
    }

    // 辅助函数
    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void xorSwap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public void printArr(int[] arr){
        for(int t : arr){
            System.out.print(t + " ");
        }
        System.out.println();
    }

    // 选择排序测试
    @Test
    public void test1(){
        // 对数器测试
        int testTimes = 5000;
        int maxSize = 1000;
        int maxNum = 1000;
        int[] arr1 = NumComparator.generateArray(maxSize, maxNum);
        int[] arr2 = NumComparator.copyArray(arr1);

        heapSort(arr1);
        Arrays.sort(arr2);
        for(int i = 0; i < testTimes; i++){
            if(!NumComparator.comparator(arr1, arr2)){
                System.out.println("未通过对数器测试");
                return;
            }
        }
        System.out.println("通过对数器测试");
    }

    // 冒泡排序测试
    @Test
    public void test2(){
        // 对数器测试
        int testTimes = 5000;
        int maxSize = 1000;
        int maxNum = 1000;

        for(int i = 0; i < testTimes; i++){
            int[] arr1 = NumComparator.generateArray(maxSize, maxNum);
            int[] arr2 = NumComparator.copyArray(arr1);
            Arrays.sort(arr1);
//            bubbleSort(arr2);
            if(!NumComparator.comparator(arr1, arr2)){
                System.out.println("未通过对数器测试");
                return;
            }
        }
        System.out.println("通过对数器测试");
    }

    // 插入排序测试
    @Test
    public void test3(){
        // 对数器测试
        int testTimes = 5000;
        int maxSize = 1000;
        int maxNum = 1000;

        for(int i = 0; i < testTimes; i++){
            int[] arr1 = NumComparator.generateArray(maxSize, maxNum);
            int[] arr2 = NumComparator.copyArray(arr1);
            Arrays.sort(arr1);
            insertionSort(arr2);
            if(!NumComparator.comparator(arr1, arr2)){
                System.out.println("未通过对数器测试");
                return;
            }
        }
        System.out.println("通过对数器测试");
    }

    // 归并排序测试
    @Test
    public void test4(){
        // 对数器测试
        int testTimes = 50000;
        int maxSize = 1000;
        int maxNum = 1000;

        for(int i = 0; i < testTimes; i++){
            int[] arr1 = NumComparator.generateArray(maxSize, maxNum);
            int[] arr2 = NumComparator.copyArray(arr1);
            Arrays.sort(arr1);
            mergeSort(arr2, 0, arr2.length - 1);
            if(!NumComparator.comparator(arr1, arr2)){
                System.out.println("未通过对数器测试");
                return;
            }
        }
        System.out.println("通过对数器测试");
    }

    // 快速排序的对数器测试
    @Test
    public void test5(){
        // 对数器测试
        int testTimes = 50000;
        int maxSize = 1000;
        int maxNum = 1000;

        for(int i = 0; i < testTimes; i++){
            int[] arr1 = NumComparator.generateArray(maxSize, maxNum);
            int[] arr2 = NumComparator.copyArray(arr1);
            Arrays.sort(arr1);
            quickSort(arr2);
            if(!NumComparator.comparator(arr1, arr2)){
                System.out.println("未通过对数器测试");
                return;
            }
        }
        System.out.println("通过对数器测试");
    }

    @Test
    public void test6(){
        int[] arr = {1, 2, 3, 4, 5};

        long time1 = System.currentTimeMillis();
        for(int i = 0; i < 1000; ++i) {
            selectionSort(arr);
        }
        long time2 = System.currentTimeMillis();
        for (int i = 0;i < 1000; ++i) {
            quickSort(arr);
        }
        long time3 = System.currentTimeMillis();

        System.out.println("sel" + (time2 - time1));
        System.out.println("quick" + (time3 - time2));
    }


}
