package com.kojikoji.java;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName findMaximizedCapital
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/12/29 19:52
 * @Version
 */

public class findMaximizedCapital {
    public class Node{
        int p;
        int c;
        public Node(int p, int c){
            this.p = p;
            this.c = c;
        }
    }

    public class MinCostComparator implements Comparator<Node>{
        public int compare(Node n1, Node n2){
            return n1.c - n2.c;
        }
    }

    public class MaxProfitComparator implements Comparator<Node>{
        public int compare(Node n1, Node n2){
            return n1.p - n2.p;
        }
    }

    public  int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital){
        Node[] nodes = new Node[Profits.length];
        for(int i = 0; i < Profits.length; ++i){
            nodes[i] = new Node(Profits[i], Capital[i]);
        }

        PriorityQueue<Node> minCostProgram = new PriorityQueue<>();
        PriorityQueue<Node> maxProfitProgram = new PriorityQueue<>();

        for(Node node : nodes){
            minCostProgram.add(node);
        }

        for(int i = 0; i < k; i++){
            while(!minCostProgram.isEmpty() && minCostProgram.peek().c <= W){
                maxProfitProgram.add(minCostProgram.poll());
            }
            if(maxProfitProgram.isEmpty()) {
                return W;
            }
            W += maxProfitProgram.poll().p;
        }

        return W;
    }

}
