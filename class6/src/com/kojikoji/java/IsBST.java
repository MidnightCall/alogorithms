package com.kojikoji.java;

import org.junit.Test;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.awt.geom.CubicCurve2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName IsBST
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/15 14:34
 * @Version
 */

public class IsBST {
    private class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public long preVal = Long.MIN_VALUE;

    public boolean checkBST(Node head){
        if(head == null){
            return true;
        }
        boolean leftIsBST = true;
        leftIsBST = checkBST(head.left);

        if(!leftIsBST){
            return false;
        }
        if(head.value <= preVal){
            return false;
        }else{
            preVal = head.value;
        }

        return checkBST(head.right);
    }

    public boolean checkBST2(Node head) {
        if(head == null){
            return true;
        }

        LinkedList<Node> nodeList = new LinkedList<>();
        process(head, nodeList);
        int preValue = Integer.MIN_VALUE;

        for(Node n : nodeList){
            if(n.value < preValue){
                return false;
            }
            preValue = n.value;
        }

        return true;
    }

    public void process(Node node, LinkedList<Node> inOrderList) {
        if(node == null){
            return;
        }

        process(node.left, inOrderList);

        inOrderList.add(node);

        process(node.right, inOrderList);
    }

    public boolean checkBST3(Node head){
        if(head == null){
            return true;
        }

        // 中序遍历
        Stack<Node> stack = new Stack<>();
        int curVal = Integer.MIN_VALUE;
        while(stack != null || head != null){
            if(head != null){
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                // 中序操作位置
                if(head.value < curVal){
                    return false;
                }else {
                    curVal = head.value;
                }
                head = head.right;
            }
        }
        return true;
    }

    public boolean checkBST4(Node head){
        return process(head).isBST;
    }

    private class ReturnType{
        public boolean isBST;
        public int max;
        public int min;

        public ReturnType(boolean isBST, int max, int min){
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public ReturnType process(Node head){
        if(head == null){
            return null;
        }

        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.right);

        int max = head.value;
        int min = head.value;
        if(leftData != null){
            max = Math.max(max, leftData.max);
            min = Math.min(min, leftData.min);
        }
        if(rightData != null){
            max = Math.max(max, rightData.max);
            min = Math.min(min, rightData.min);
        }

        boolean isBST = true;
        if(leftData != null && (!leftData.isBST || leftData.max > head.value)){
            isBST = false;
        }
        if(rightData != null && (!rightData.isBST || rightData.min < head.value)){
            isBST = false;
        }

        return new ReturnType(isBST, max, min);
    }

    @Test
    public void test(){
        Node head = new Node(5);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(4);
        head.right = new Node(8);
        head.right.left = new Node(7);
        head.right.right = new Node(9);
        boolean res = checkBST2(head);
        System.out.println(res);
    }
}
