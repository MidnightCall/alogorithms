package com.kojikoji.sort;

import java.util.Arrays;

/**
 * @ClassName Test
 * @Description 测试用模块
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/6 20:32
 * @Version
 */

public class Test {
    @org.junit.Test
    public void otherTest(){
        System.out.println(6 << 1 + 2);
    }

    // 排序的单数组测试
    @org.junit.Test
    public void test(){
        int[] arr = new int[]{1,4,2,3,5};
        new Sort().countSort(arr);
        for(int i : arr){
            System.out.println(i);
        }
    }

    // 堆排序测试
    @org.junit.Test
    public void test1(){
        // 对数器测试
        int testTimes = 500000;
        int maxSize = 10000;
        int maxNum = 10000;
        int[] arr1 = NumComparator.generateArray(maxSize, maxNum);
        int[] arr2 = NumComparator.copyArray(arr1);
        Arrays.sort(arr1);
        new Sort().baseQuickSort(arr2);
        for(int i = 0; i < testTimes; i++){
            if(!NumComparator.comparator(arr1, arr2)){
                System.out.println("未通过对数器测试");
                return;
            }
        }
        System.out.println("通过对数器测试");
    }
    @org.junit.Test
    public void test3() {
        String s =  "00110";
        System.out.println(minFlipsMonoIncr(s));
    }

    public int minFlipsMonoIncr(String s) {
        int length = s.length();
        if (length == 0) {
            return 0;
        }
        int[][] dp = new int[2][2];
        dp[0][0] = s.charAt(0) == '0' ? 0 : 1;
        dp[0][1] = s.charAt(0) == '1' ? 0 : 1;
        for (int i = 1; i < length; ++i) {
            char ch = s.charAt(i);
            int prev0 = dp[(i-1)%2][0];
            int prev1 = dp[(i-1)%2][1];
            dp[i%2][0] = prev0 + (ch == '0' ? 0 : 1);
            dp[i%2][1] = Math.min(prev0, prev1) + (ch == 0 ? 1 : 0);
        }
        int last = length-1;
        return Math.min(dp[last%2][0], dp[last%2][1]);
    }

    class ListNode {
        public int val;
        public ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }

        return mergeList(lists, 0, lists.length-1);
    }
    public ListNode mergeList(ListNode[] lists, int l, int r) {
        if (l >= r) {
            return lists[l];
        }
        int m = l + ((r-l) >> 1);
        ListNode l1 = mergeList(lists, l, m-1);
        ListNode l2 = mergeList(lists, m, r);
        return merge(l1, l2);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}
