package 左程云.class06;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-06 13:11
 */
public class BFS {

    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.println(temp.value);
            for (Node next : temp.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

}
