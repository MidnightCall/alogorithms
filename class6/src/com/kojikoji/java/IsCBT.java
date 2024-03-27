package com.kojikoji.java;

import org.junit.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @ClassName IsCBT
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/15 15:30
 * @Version
 */

public class IsCBT {
    private class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public boolean isCBT(Node head){
        if(head == null){
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(head);
        boolean leaf = false;
        Node left = null;
        Node right = null;

        while(!queue.isEmpty()){
            head = queue.pop();
            left = head.left;
            right = head.right;

            // 1.有右子树没有左子树 2.当某个子树为不完全子树时，后续节点不为叶节点
            // 满足上述条件之一时，返回false
            if((left == null && right != null) || (leaf && (left != null || right != null))){
                return false;
            }

            if(left != null) {
                queue.add(left);
            }
            if(right != null){
                queue.add(right);
            }else{
                leaf = true;
            }
        }
        return true;
    }

    @Test
    public void test(){
        Node head = new Node(5);
        head.left = new Node(3);
        head.left.left = new Node(1);
        head.left.right = new Node(4);
        head.right = new Node(8);
        head.right.left = new Node(7);

        boolean res = isCBT(head);
        System.out.println(res);
    }
}
