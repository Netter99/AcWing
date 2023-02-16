package 左程云.基础.class06_图;

import java.util.ArrayList;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-06 11:13
 */
public class Node {
    // 点的值
    public int value;
    // 入度
    public int in;
    // 出度
    public int out;
    // 从当前Node发散出去（出度），直接邻居
    public ArrayList<Node> nexts;
    // 从当前Node，有多少发散出去的边（出边），拥有的边
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
