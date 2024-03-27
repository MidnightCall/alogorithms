package com.kojikoji.java;

import org.junit.Test;

/**
 * @ClassName IsBalancedTree
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/15 20:46
 * @Version
 */

public class IsBalancedTree {
    private class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public boolean isBalanced(Node head){
        return process(head).isBalance;
    }

    private class ReturnType{
        public boolean isBalance;
        public int height;

        public ReturnType(boolean isBalance, int height){
            this.height = height;
            this.isBalance = isBalance;
        }
    }

    public ReturnType process(Node head){
        if(head == null){
            return new ReturnType(true, 0);
        }
        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.right);

        boolean isBalance;
        int height;

        isBalance = (leftData.isBalance && rightData.isBalance) &&
                    (Math.abs(leftData.height - rightData.height) < 2);
        height = Math.max(leftData.height, rightData.height) + 1;

        return new ReturnType(isBalance, height);
    }

    @Test
    public void test(){
        Node head = new Node(5);
        head.left = new Node(4);
        head.left.left = new Node(2);
        head.left.left.left = new Node(1);
        head.left.left.left.left = new Node(1);
        head.left.right = new Node(5);
        head.right = new Node(7);
        head.right.left = new Node(6);
        boolean res = isBalanced(head);
        System.out.println(res);
    }
}
