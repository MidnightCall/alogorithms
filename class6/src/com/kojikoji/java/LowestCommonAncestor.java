package com.kojikoji.java;

import org.w3c.dom.Node;

import java.util.*;

/**
 * @ClassName LowestCommonAncestor
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/15 21:33
 * @Version
 */

public class LowestCommonAncestor {
    private class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public Node lowestAncestor(Node head, Node o1, Node o2){
        if(head == null || head == o1 || head == o2){
            return head;
        }
        Node left = lowestAncestor(head.left, o1, o2);
        Node right = lowestAncestor(head.right, o1, o2);
        if(left != null && right != null){
            return head;
        }
        return left == null ? right : left;
    }


}
