package 左程云.class06;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-06 11:15
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
