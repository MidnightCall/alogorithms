package com.kojikoji.java;

import javax.swing.tree.TreeCellRenderer;
import java.time.chrono.ThaiBuddhistEra;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName SerializeAndReconstructTree
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/3 21:22
 * @Version
 */

public class SerializeAndReconstructTree {
    public static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static String serialize(Node head){
        if(head == null){
            return "#_";
        }
        String res = head.value + "_";
        res += serialize(head.left);
        res += serialize(head.right);
        return res;
    }

    public static Node reconstruct(String preString){
        String[] values = preString.split("_");
        Queue<String> queue = new LinkedList<>();
        for(String s : values){
            queue.add(s);
        }
        return rec(queue);
    }

    public static Node rec(Queue<String> queue){
        String value = queue.poll();
        if(value == "#"){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = rec(queue);
        head.right = rec(queue);
        return head;
    }
    
}
