package 左程云.class05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 判断是否是搜索二叉树  --- 中序遍历是否单调递增
 * @author: Netter
 * @date: 2023-02-02 10:33
 */
public class TestBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }
    }

    public static int preValue = Integer.MIN_VALUE;

    public static boolean checkBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean checkLeft = checkBST(head.left);
        if (!checkLeft) {
            return false;
        }
        if (head.value <= preValue) {
            return false;
        }else {
            preValue = head.value;
        }
        return checkBST(head.right);
    }

    public static boolean checkBST2(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        int preValue = Integer.MIN_VALUE;
        while (!queue.isEmpty() || head != null) {
            if (head != null) {
                queue.add(head);
                head = head.left;
            }else {
                head = queue.poll();
                if (head.value <= preValue) {
                    return false;
                }else {
                    preValue = head.value;
                }
                head = head.right;
            }
        }
        return true;
    }

}
