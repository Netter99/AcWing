package 左程云.class04;

import java.util.*;

/**
 * @description:
 * @author: Netter
 * @date: 2023-01-31 12:10
 */
public class HashAndTree {

    public static class Node {
        int val;
        Node next;
        public Node(){}

        public Node(int val) {
            this.val = val;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.val - o2.val;
        }
    }

    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(3);
        System.out.println(hashSet.contains(3));
        hashSet.remove(3);
        System.out.println(hashSet.contains(3));

        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "123");
        hashMap.put(1, "456");
        hashMap.put(2, "aaa");
        System.out.println(hashMap.containsKey(1));
        System.out.println(hashMap.get(1));
        System.out.println(hashMap.get(2));

        hashMap.remove(1);
        System.out.println(hashMap.containsKey(1));

        System.out.println("=============1===========");

        TreeSet<Node> treeSet = new TreeSet<>();    //红黑树
        Node node1 = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(5);
        // 以下代码会报错，因为没有提供Node类型的比较器
        try {
            treeSet.add(node1);
            treeSet.add(node2);
            treeSet.add(node3);
        }catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
        }

        treeSet = new TreeSet<>(new NodeComparator());
        try {
            treeSet.add(node1);
            treeSet.add(node2);
            treeSet.add(node3);
            System.out.println("结点完成加入");
        }catch (Exception e) {
            System.out.println("错误信息：" + e.getMessage());
        }

        System.out.println("=============2===========");

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(7, "我是7");
        treeMap.put(5, "我是5");
        treeMap.put(4, "我是4");
        treeMap.put(3, "我是3");
        treeMap.put(9, "我是9");
        treeMap.put(2, "我是2");
        System.out.println(treeMap.containsKey(5));
        System.out.println(treeMap.get(5));
        System.out.println(treeMap.firstKey() + "，我最小");
        System.out.println(treeMap.lastKey() + "，我最大");
        System.out.println(treeMap.floorKey(8) + "，在表中所有<=8的数中，我离8最近");
        System.out.println(treeMap.ceilingKey(8) + "，在表中所有>=8的数中，我离8最近");
        treeMap.remove(5);
        System.out.println(treeMap.get(5) + "，删除了");
    }


}
