package com.kojikoji.exer;

import com.kojikoji.sort.NumComparator;
import org.junit.Test;

import java.nio.file.ClosedWatchServiceException;
import java.util.Arrays;

/**
 * @ClassName Exer
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/6 16:46
 * @Version
 */

public class Exer {
    // 归并排序
    public void mergeSort(int[] arr, int L, int R){
        if(L == R)
            return;
        int M = L + ((R - L) >> 1);
        mergeSort(arr, L, M);
        mergeSort(arr, M+1, R);
        merge(arr, L, M, R);
    }

    public void merge(int[] arr, int L, int M, int R){
        int[] help = new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while(p1 <= M && p2 <= R){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= M){
            help[i++] = arr[p1++];
        }
        while(p2 <= R){
            help[i++] = arr[p2++];
        }
        for(int j = 0; j < help.length; j++){
            arr[L+j] = help[j];
        }
    }

    // 快速排序
    public void quickSort(int[] arr, int L, int R){
        if(L < R){
            swap(arr, R, L + (int) (Math.random()*(R-L+1)));
            int p[] = partition(arr, L, R);
            quickSort(arr, L, p[0]-1);
            quickSort(arr, p[1]+1, R);
        }
    }

    public int[] partition(int[] arr, int L, int R){
        int less = L - 1;
        int more = R;
        while(L < more){
            if(arr[L] < arr[R]){
                swap(arr, ++less, L++);
            }else if(arr[L] > arr[R]){
                swap(arr, --more, L);
            }else {
                L++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }

    // 堆排序
    public void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        // 制造大根堆
        // 法一：对根节点重复进行heapInsert操作
        for(int i = 0; i < arr.length; i++){
            heapInsert(arr, i);
        }

        // 交换根节点与末位叶节点，并重复进行heapify操作
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while(heapSize > 0){
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    // 指定节点向上移动
    public void heapInsert(int[] arr, int index){
         while (arr[index] > arr[(index - 1) / 2]){
             swap(arr, index, (index - 1) / 2);
             index = (index - 1) / 2;
         }
    }

    // 指定节点向下移动
    public void heapify(int[] arr, int index, int heapSize){
        int left = (index << 1) + 1;
        while(left < heapSize){
            int largest = arr[left+1] > arr[left] && left+1 < heapSize ?
                            left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if(index == largest){
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = (index << 1) + 1;
        }
    }

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    @Test
    public void test1(){
        // 对数器测试
        int testTimes = 5000;
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
            heapSort(arr2);
            if(!NumComparator.comparator(arr1, arr2)){
                System.out.println("未通过对数器测试");
                return;
            }
        }
        System.out.println("通过对数器测试");
    }

    // 排序的单数组测试
    @Test
    public void test(){
        System.out.println(-1 >> 1);
    }
}
