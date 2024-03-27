import org.junit.Test;

/**
 * @ClassName MaxHappy
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2023/1/4 14:43
 * @Version
 */

public class MaxHappy {
    public class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }


    public int getMaxHappy(Node head){
        ReturnType data = process(head);
        return Math.max(data.maxHappyCome, data.maxHappyNotCome);
    }

    public class ReturnType{
         int maxHappyCome;
         int maxHappyNotCome;
         public ReturnType(int maxHappyCome, int maxHappyNotCome){
             this.maxHappyCome = maxHappyCome;
             this.maxHappyNotCome = maxHappyNotCome;
         }
    }

    public ReturnType process(Node root){
        if(root == null){
            return new ReturnType(0, 0);
        }
        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);

        int maxHappyCome = leftData.maxHappyNotCome + rightData.maxHappyNotCome + root.value;
        int maxHappyNotCome = Math.max(leftData.maxHappyCome, leftData.maxHappyNotCome) +
                            Math.max(rightData.maxHappyCome, rightData.maxHappyNotCome);

        return new ReturnType(maxHappyCome, maxHappyNotCome);
    }

    @Test
    public void test(){
        Node root = new Node(3);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(3);
        root.right.right = new Node(1);
        System.out.println(getMaxHappy(root));

        Node root2 = new Node(3);
        root2.left = new Node(4);
        root2.right = new Node(5);
        root2.left.left = new Node(1);
        root2.left.right = new Node(3);
        root2.right.right = new Node(1);
        System.out.println(getMaxHappy(root2));
    }
}
