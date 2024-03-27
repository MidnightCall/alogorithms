package com.kojikokji.java;


import org.junit.Test;

import java.util.*;

/**
 * @ClassName TreeMaxWidth
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/3 15:34
 * @Version
 */

public class TreeMaxWidth {
    public class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

    public void bfs(TreeNode root){
        Queue<TreeNode> list = new LinkedList<>();
        TreeNode curr = root;
        list.add(curr);

        while(!list.isEmpty()){
            curr = list.poll();
            System.out.print(curr.val + " ");

            if(curr.left != null){
                list.add(curr.left);
            }
            if(curr.right != null){
                list.add(curr.right);
            }
        }
        System.out.println();
    }

    public int getMaxWidth(TreeNode root){
        if(root == null){
            return 0;
        }
        int maxWidth = Integer.MIN_VALUE;
        int curLevel = 1;
        int curWidth = 0;
        TreeNode curr = root;
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> levelMap = new HashMap<>();
        queue.add(curr);
        levelMap.put(curr, 1);

        while(!queue.isEmpty()){
            curr = queue.poll();

            if(curr.left != null){
                levelMap.put(curr.left, curLevel + 1);
                queue.add(curr.left);
            }
            if(curr.right != null){
                levelMap.put(curr.right, curLevel + 1);
                queue.add((curr.right));
            }
            if(levelMap.get(curr) > curLevel){
                ++curLevel;
                curWidth = 1;
            }else{
                ++curWidth;
            }

            maxWidth = Math.max(curWidth, maxWidth);
        }
        return maxWidth;
    }

    @Test
    public void test(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(8);
        root.right.left.left = new TreeNode(9);
        root.right.right.right = new TreeNode(3);

        bfs(root);
        int width = getMaxWidth(root);
        System.out.println(width);
    }


}
