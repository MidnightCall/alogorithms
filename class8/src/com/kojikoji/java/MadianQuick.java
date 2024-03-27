package com.kojikoji.java;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName MadianQuick
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/12/31 20:09
 * @Version
 */

public class MadianQuick {
    public class MinHeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return 0;
        }
    }

    public class MaxHeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer n1, Integer n2){
            return n2 - n1;
        }
    }

    public class MedianFinder{
        PriorityQueue<Integer> maxHeap;
        PriorityQueue<Integer> minHeap;

        public MedianFinder(){
            maxHeap = new PriorityQueue<>(new MaxHeapComparator());
            minHeap = new PriorityQueue<>(new MinHeapComparator());
        }

        private void modifySize(){
            if(maxHeap.size() == minHeap.size() + 2){
                minHeap.add(maxHeap.poll());
            }
            if(minHeap.size() == minHeap.size() + 2){
                maxHeap.add(minHeap.poll());
            }
        }

        public void addNumber(int n){
            if(maxHeap.isEmpty() || n < maxHeap.peek()){
                maxHeap.add(n);
            }else{
                minHeap.add(n);
            }
            modifySize();
        }

        public Integer findMedian(){
            int maxSize = maxHeap.size();
            int minSize = minHeap.size();
            if(maxSize + minSize == 0){
                return null;
            }
            int maxHeapTop = maxHeap.peek();
            int minHeapTop = minHeap.peek();

            if(((maxSize + minSize) & 1) == 0){
                return (maxHeapTop + minHeapTop) >> 1;
            }
            return maxSize > minSize ? maxHeapTop : minHeapTop;
        }
    }
}
