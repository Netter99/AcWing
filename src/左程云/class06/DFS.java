package 左程云.class06;

import java.util.HashSet;
import java.util.Stack;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-06 13:17
 */
public class DFS {

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node temp = stack.pop();
            for (Node next : temp.nexts) {
                if (!set.contains(next)) {
                    stack.push(temp);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

}
