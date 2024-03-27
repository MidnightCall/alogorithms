package com.kojikoji.java;

import org.junit.Test;

import java.awt.*;

/**
 * @ClassName IsFullTree
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/15 20:57
 * @Version
 */

public class IsFullTree {
    private class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public boolean isFull(Node head){
        return process(head).isFull;
    }

    private class ReturnType{
        public boolean isFull;
        public int nums;
        public int height;

        public ReturnType(boolean isFull, int nums, int height){
            this.isFull = isFull;
            this.nums = nums;
            this.height = height;
        }
    }

    public ReturnType process(Node head){
        if(head == null){
            return new ReturnType(true, 0, 0);
        }
        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.right);

        boolean isFull;
        int nums;
        int height;

        nums = leftData.nums + rightData.nums + 1;
        height = Integer.max(leftData.height, rightData.height) + 1;
        isFull = (leftData.isFull && rightData.isFull) && (nums == (1 << height) - 1);

        return new ReturnType(isFull, nums, height);
    }

    @Test
    public void test(){
        Node head = new Node(5);
        head.left = new Node(4);
        head.left.left = new Node(2);
        head.left.right = new Node(5);
        head.right = new Node(7);
        head.right.left = new Node(6);
        head.right.right = new Node(8);
        head.right.right.right = new Node(9);
        boolean res = isFull(head);
        System.out.println(res);
    }
}