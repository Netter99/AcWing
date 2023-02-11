package 左程云.class06;

import java.util.*;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-06 14:12
 */
public class Kruskal {

    public static class MySets {
        public HashMap<Node, List<Node>> setMap;

        public MySets(List<Node> nodes) {
            for (Node node : nodes) {
                List<Node> set = new ArrayList<>();
                set.add(node);
                setMap.put(node, set);
            }
        }

        public boolean isSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        public void union(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for(Node toNode : toSet) {
                fromSet.add(toNode);
                setMap.put(toNode, fromSet);
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Node> kruskalMST(Graph graph) {
        //MySets unionFind = new MySets(graph.nodes.values());
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        Set<Edge> result = new HashSet<>();
        //todo
        return null;
    }

}
