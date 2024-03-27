package com.kojikoji.java;

import java.awt.geom.CubicCurve2D;

/**
 * @ClassName SmallerEqualBigger
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/10 11:36
 * @Version
 */

public class SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition1(Node head, int pivot) {
        if(head == null){
            return head;
        }
        Node curr = head;
        int i = 0;
        while(curr != null){
            curr = curr.next;
            i++;
        }
        Node[] nodes = new Node[i];
        curr = head;
        for(i = 0; i < nodes.length; i++){
            nodes[i] = curr;
            curr = curr.next;
        }
        arrPartition(nodes, pivot);
        for(i = 0; i < nodes.length - 1; i++){
            nodes[i].next = nodes[i+1];
        }
        nodes[i].next = null;
        return nodes[0];
    }

    public static void arrPartition(Node[] nodeArr, int pivot) {
        int less = -1;
        int more = nodeArr.length;
        int curr = 0;

        while(curr < more){
            if(nodeArr[curr].value < pivot){
                swap(nodeArr, ++less, curr++);
            }else if(nodeArr[curr].value == pivot){
                curr++;
            }else{
                swap(nodeArr, --more, curr);
            }
        }
    }

    public static void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        Node next = null;

        while(head != null){
            next = head.next;
            head.next = null;
            if(head.value < pivot){
                if(sH == null){
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if(eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = eT.next;
                }
            }else{
                if(bH == null){
                    bH = head;
                    bT = head;
                }else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }

        if(sH != null){
            sT.next = eH;
            eT = eT == null? sT : eT;
        }

        if(eH != null){
            eT.next = bH;
        }

        return sH != null? sH:eH != null?eH:bH;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//        head1 = listPartition1(head1, 5);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }
}
