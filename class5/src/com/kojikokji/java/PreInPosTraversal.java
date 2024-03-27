package com.kojikokji.java;

import jdk.swing.interop.SwingInterOpUtils;
import org.junit.Test;

import javax.swing.plaf.synth.SynthLookAndFeel;
import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.Stack;

/**
 * @ClassName PreInPosTraversal
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/10/12 13:32
 * @Version
 */

public class PreInPosTraversal {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void preOrderRecur(Node head) {
        if(head == null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(Node head) {
        if(head == null){
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    public static void posOrderRecur(Node head) {
        if(head == null){
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    public static void preOrderUnRecur(Node head) {
        System.out.println("pre-order:");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while(!stack.isEmpty()){
                Node node = stack.pop();
                System.out.print(node.value + " ");
                if(node.right != null){
                    stack.push(node.right);
                }
                if(node.left != null){
                    stack.push(node.left);
                }
            }
        }
    }

    public static void inOrderUnRecur(Node head) {
        System.out.println("in-order:");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            while(!stack.isEmpty() || head != null){
                if(head != null){
                    stack.push(head);
                    head = head.left;
                }else{
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    public static void posOrderUnRecur1(Node head) {

    }

    public static void posOrderUnRecur2(Node h) {

    }

    @Test
    public void test(){
        Node head = new Node(18);
        head.left = new Node(10);
        head.right = new Node(73);
        head.left.left = new Node(5);
        head.right.left = new Node(68);
        head.right.right = new Node(99);
        head.right.left.left = new Node(27);
        head.right.left.left.left = new Node(25);
        head.right.left.left.right = new Node(41);
        head.right.left.left.right.left = new Node(32);
        head.right.left.left.right.right = new Node(51);
        inOrderRecur(head);
        System.out.println();
        inOrderUnRecur(head);
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        posOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        inOrderUnRecur(head);
        posOrderUnRecur1(head);
        posOrderUnRecur2(head);

    }
}
