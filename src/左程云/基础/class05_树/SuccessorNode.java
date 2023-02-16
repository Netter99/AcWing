package 左程云.基础.class05_树;

/**
 * @description: 寻找一个结点的后继结点（中序遍历的后一个结点）
 * @author: Netter
 * @date: 2023-02-03 12:21
 */
public class SuccessorNode {

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        }else { // 无右子树
            Node parent = node.parent;
            while (parent != null && parent.left != node) { // 当前结点是父节点的右孩子
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    public static Node getLeftMost(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


}
