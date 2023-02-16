package 左程云.基础.class06_图;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-06 11:11
 */
public class Graph {

    // 第i个点是Node
    public HashMap<Integer, Node> nodes;
    // 边
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

}
