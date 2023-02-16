package 左程云.基础.class05_树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-03 12:44
 */
public class SerializeAndReconstructTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 以head为头的树，请序列化成字符串返回
    public static String serialByPre(Node head) {
        if (head == null) {
            return "#_";
        }
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    public static Node reconByPreString(String preStr) {
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.add(values[i]);
        }
        return reconProOrder(queue);
    }

    public static Node reconProOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconProOrder(queue);
        head.right = reconProOrder(queue);
        return head;
    }


}
