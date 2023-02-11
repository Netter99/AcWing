package 左程云.class06;

import java.util.*;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-06 13:41
 */
public class TopologySort {

    public static List<Node> sortedTopology(Graph graph) {
        // key：某一个node
        // value：剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }

        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node temp = zeroInQueue.poll();
            result.add(temp);
            for (Node next : temp.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return result;
    }

}
