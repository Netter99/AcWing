package 左程云.class05;

/**
 * @description: 是否是平衡二叉树
 * @author: Netter
 * @date: 2023-02-02 16:32
 */
public class IsBalancedTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }
    }

    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean isBalanced(Node head) {
        return process(head).isBalanced;
    }

    /** 写递归的思路：
     *      1.确定base状态的处理
     *      2.保证当前情况下也能返回需要的信息（返回类型决定）
     *   注意：在递归内部中使用黑盒，并利用黑盒获取到的信息，来解析当前情况 -> 得到当前情况的返回状态
     */
    public static ReturnType process(Node head) {
        if (head == null) { // base
            return new ReturnType(true, 0);
        }
        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.left);

        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
                            && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced, height);
    }

}
