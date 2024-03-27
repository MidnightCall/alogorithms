import org.junit.Test;

import java.util.List;

/**
 * @ClassName MaxDistanceInTree
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/4 14:17
 * @Version
 */

public class MaxDistanceInTree {
    public class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }
    public int getMaxDistance(Node root){
        return process(root).maxDistance;
    }
    public class ReturnType{
        int maxHeight;
        int maxDistance;
        public ReturnType(int maxDistance, int maxHeight){
            this.maxDistance = maxDistance;
            this.maxHeight = maxHeight;
        }
    }

    public ReturnType process(Node root){
        if(root == null){
            return new ReturnType(0, 0);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);

        int subMaxDistance = Math.max(leftData.maxDistance, rightData.maxDistance);
        int maxDistance = Math.max(subMaxDistance, 1 + leftData.maxHeight + rightData.maxHeight);
        int maxHeight = Math.max(leftData.maxHeight, rightData.maxHeight) + 1;

        return new ReturnType(maxDistance, maxHeight);
    }

    @Test
    public void test(){
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.left.left = new Node(8);
        head1.right.left.right = new Node(9);
        System.out.println(getMaxDistance(head1));

        Node head2 = new Node(1);
        head2.left = new Node(2);
        head2.right = new Node(3);
        head2.right.left = new Node(4);
        head2.right.right = new Node(5);
        head2.right.left.left = new Node(6);
        head2.right.right.right = new Node(7);
        head2.right.left.left.left = new Node(8);
        head2.right.right.right.right = new Node(9);
        System.out.println(getMaxDistance(head2));
    }

}
