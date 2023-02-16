package 左程云.基础.class05_树;

import java.util.Stack;

/**
 * @description: 遍历代码（递归 + 非递归）
 * @author: Netter
 * @date: 2023-02-01 18:46
 */
public class PreinPosTraversal {

    public static class Node {
        public Node left;
        public Node right;
        public int value;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }
    }

    /*
    递归
     */
    // 前序遍历
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    // 中序遍历
    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    // 后序遍历
    public static void postOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    /*
    非递归
     */
    // 前序遍历
    public static void preOrderUnRecur(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.print(head.value + " ");
            if (head.right != null) {
                stack.add(head.right);
            }
            if (head.left != null) {
                stack.add(head.left);
            }
        }
        System.out.println();
    }

    // 中序遍历
    public static void inOrderUnRecur(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

    // 后序遍历
    public static void postOrderUnRecur(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(head);
        while (!s1.isEmpty()) {
            head = s1.pop();
            s2.push(head);
            if (head.left != null) {
                s1.push(head.left);
            }
            if (head.right != null) {
                s1.push(head.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().value + " ");
        }
        System.out.println();
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
        System.out.print("pre-order:");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order:");
        inOrderRecur(head);
        System.out.println();
        System.out.print("post-order:");
        postOrderRecur(head);
        System.out.println();

        System.out.println();
        System.out.print("pre-order:");
        preOrderUnRecur(head);
        System.out.print("in-order:");
        inOrderUnRecur(head);
        System.out.print("post-order:");
        postOrderUnRecur(head);

    }

}
