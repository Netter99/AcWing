package 左程云.class05;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description: 判断是否是完全二叉树
 * @author: Netter
 * @date: 2023-02-02 14:55
 */
public class IsCBT {

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

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node l = null;
        Node r = null;
        boolean leaf = false;
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((l == null && r != null) || (leaf && (l != null || r != null))) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static class Info {
        public int height;
        public int nodes;
        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        Info data = process(head);
        // nodes == 2 ^ data.height - 1
        return data.nodes == (1 << data.height - 1);
    }

    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftData = process(x.left);
        Info rightData = process(x.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        int nodes = leftData.nodes + rightData.nodes + 1;
        return new Info(height, nodes);
    }

}
