package 左程云.class05;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Handler;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-01 20:08
 */
public class TreeMaxWidth {

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

    public static int getMaxWidth(Node head) {
        if (head  == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (curLevel == levelMap.get(cur)) {
                curLevelNodes++;
            }else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
            if (cur.left != null) {
                levelMap.put(cur.left, levelMap.get(cur) + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, levelMap.get(cur) + 1);
                queue.add(cur.right);
            }
        }
        return max;
    }

    // 不适用哈希表
    public static int getMaxWidth2(Node head) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;
        Node nextEnd = null;
        int curLevelNodes = 0;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                queue.add(head.left);
                nextEnd = head.left;
            }
            if (head.right != null) {
                queue.add(head.right);
                nextEnd = head.right;
            }
            curLevelNodes++;
            if (head == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
                nextEnd = null;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);

        System.out.println(getMaxWidth(head));
        System.out.println(getMaxWidth2(head));
    }

}
