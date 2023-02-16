package 左程云.基础.class05_树;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-02 20:15
 */
public class IsBST {

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

    public static class ReturnData {
        public boolean isBST;
        public int min;
        public int max;

        public ReturnData(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static ReturnData isBST(Node x) {
        if (x == null) {
            return null;
        }
        ReturnData leftData = isBST(x.left);
        ReturnData rightData = isBST(x.right);

        int min = x.value;
        int max = x.value;
        if (leftData != null) {
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if (rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }
        boolean isBST = true;
        if (leftData != null && (!leftData.isBST || leftData.max >= x.value)) {
            isBST = false;
        }
        if (rightData != null && (!rightData.isBST || x.value >= rightData.min)) {
            isBST = false;
        }
        return new ReturnData(isBST, min, max);
    }

}
