import org.junit.Test;

import javax.swing.tree.TreeNode;
import java.util.*;

/**
 * @ClassName SlidingWindowMaxArray
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/5 17:14
 * @Version
 */

public class SlidingWindowMaxArray {

    public int[] maxSlidingWindow(int[] nums, int k){
        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for(int i = 0; i < nums.length; ++i){
            while(!qMax.isEmpty() && nums[qMax.peekLast()] <= nums[i]){
                qMax.pollLast();
            }
            qMax.addLast(i);
            if(qMax.peekFirst() == i - k){
                qMax.pollFirst();
            }
            if(i >= k - 1){
                res[index++] = nums[qMax.peekFirst()];
            }
        }

        return res;
    }

    public void printArray(int[] arr){
        for(int num : arr){
            System.out.print(num + " ");
        }
        System.out.println();
    }

    @Test
    public void test(){
        List<Integer> list = new ArrayList<>();
        list.clear();
        list.stream().mapToInt(Integer::intValue).toArray();
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        return build(map, preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    public TreeNode build(Map<Integer, Integer> map, int[] preorder, int pBegin, int pEnd,
                          int[] inorder, int inBegin, int inEnd) {
        if (pEnd < pBegin || inEnd < inBegin) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[pBegin]);
        int index = map.get(preorder[pBegin]);
        int leftCount = index - inBegin - 1;
        node.left = build(map, preorder, pBegin+1, pBegin+1+leftCount, inorder, inBegin, index-1);
        node.right = build(map, preorder, pBegin+1+leftCount+1, pEnd, inorder, index+1, inEnd);
        return node;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int res;
    private int maxHeight = Integer.MIN_VALUE;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    public void dfs(TreeNode root, int height) {
        if (null == root) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (height > maxHeight) {
                maxHeight = height;
                res = root.val;
            }
            return;
        }
        dfs(root.left, height + 1);
        dfs(root.right, ++height);
    }

    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return new int[] {-1};
        }
        int n = nums.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        Arrays.fill(res, -1);
        for (int i = 0; i < n * 2; ++i) {
            while (!stack.isEmpty() && nums[i % n] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i & n];
            }
            stack.push(i % n);
        }
        return res;
    }

    private int getMinAbs(int[] arr) {
        if (arr == null || arr.length < 1) {
            throw new RuntimeException("错误的大小");
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= 0) {
                if (mid == 0 || arr[mid-1] <= 0) {
                    return arr[mid];
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return arr[arr.length-1];
    }
}
