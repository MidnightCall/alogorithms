package com.kojikoji.java;

import jdk.swing.interop.SwingInterOpUtils;
import org.junit.Test;
import org.w3c.dom.ls.LSException;

import java.nio.file.attribute.FileAttribute;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.List;

/**
 * @ClassName CustomizePointer
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/3/20 19:32
 * @Version
 */

public class CustomizePointer {
    class ListNode{
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
        }
    }

    /**
     * 奇数个节点的中间节点, 偶数个节点中间节点的下一个节点
     * @return
     */
    public ListNode middle(ListNode head){
        if(head == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 奇数个节点中间节点的下一个节点, 偶数个节点中间的下一个
     * @param head
     * @return
     */
    public ListNode middleNext(ListNode head){
        if(head == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
            if(fast != null){
                fast = fast.next;
            }
        }
        return slow;
    }

    /**
     * 奇数个节点的中间节点，偶数个节点中间节点的上一个节点
     * @param head
     * @return
     */
    public ListNode middlePrev(ListNode head){
        if(head == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public void printList(ListNode head){
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    @Test
    public void test(){
        // 奇数个节点
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        printList(head);
        System.out.println(middle(head).val);
        System.out.println(middleNext(head).val);
        System.out.println(middlePrev(head).val);


        System.out.println("---------------------------------------");
        // 偶数个节点
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(6);
        printList(root);
        System.out.println(middle(root).val);
        System.out.println(middleNext(root).val);
        System.out.println(middlePrev(root).val);
    }
}
