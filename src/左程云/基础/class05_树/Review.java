package 左程云.基础.class05_树;

import java.util.*;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-08 16:51
 */
public class Review {

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

    //遍历 --- 递归
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    public static void postOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.print(head.value + " ");
    }


    //遍历 --- 非递归
    public static void preOrderUnRecur(Node head) {
        if(head == null) {
            return;
        }
        Node cur = head;
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            cur = stack.pop();
            System.out.print(cur.value + " ");
            if (cur.right != null) {
                stack.add(cur.right);
            }
            if (cur.left != null) {
                stack.add(cur.left);
            }
        }
        System.out.println();
    }

    public static void inOrderUnRecur(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
                System.out.print(cur.value + " ");
                cur = cur.right;
            }
        }
        System.out.println();
    }

    public static void postOrderUnRecur(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(head);
        while (!s1.isEmpty()) {
            head = s1.pop();
            s2.push(head);
            if (head.left != null) {
                s1.push(head.left);
            }
            if (head.right != null) {
                s1.push(head.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().value + " ");
        }
        System.out.println();
    }

    public static int getMaxWidth(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1;
        int curLevelCount = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (curLevel == levelMap.get(head)) {
                curLevelCount++;
            }else {
                max = Math.max(max, curLevelCount);
                curLevel++;
                curLevelCount = 1;
            }
            if (head.left != null) {
                queue.add(head.left);
                levelMap.put(head.left, curLevel+1);
            }
            if (head.right != null) {
                queue.add(head.right);
                levelMap.put(head.right, curLevel+1);
            }
        }
        return max;
    }

    public static int getMaxWidth2(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;
        Node nextEnd = null;
        int curLevelCount = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            head = queue.poll();
            curLevelCount++;
            if (head.left != null) {
                queue.add(head.left);
                nextEnd = head.left;
            }
            if (head.right != null) {
                queue.add(head.right);
                nextEnd = head.right;
            }
            if (head == curEnd) {
                max = Math.max(max, curLevelCount);
                curLevelCount = 0;
                curEnd = nextEnd;
                nextEnd = null;
            }
        }
        return max;
    }

    static public int preValue = Integer.MIN_VALUE;

    public static boolean checkBST(Node head) {
        if (head == null) {
            return true;
        }
        boolean left = checkBST(head.left);
        if (!left) {
            return false;
        }
        if (preValue >= head.value) {
            return false;
        }else {
            preValue = head.value;
        }
        return checkBST(head.right);
    }

    public static boolean checkBST2(Node head) {
        if (head == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        int preValue = Integer.MIN_VALUE;
        while (!stack.isEmpty() || head == null) {
            if(head != null) {
                stack.add(head);
                head = head.left;
            }else {
                head = stack.pop();
                if (preValue >= head.value) {
                    return false;
                }else {
                    preValue = head.value;
                }
                head = head.right;
            }
        }
        return true;
    }

    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        boolean check = false;
        while (!queue.isEmpty()) {
            head = queue.poll();
            if ((head.left == null && head.right != null) || (check && (head.left != null || head.right != null))) {
                return false;
            }
            if (head.left != null) {
                queue.add(head.left);
            }
            if (head.right != null) {
                queue.add(head.right);
            }
            if (head.left == null || head.right == null) {
                check = true;
            }
        }
        return true;
    }

    public static class Info {
        public int height;
        public int nodes;

        public Info(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        Info info = processCBT(head);
        return info.nodes == (1 << info.height - 1);
    }

    public static Info processCBT(Node node) {
        if (node == null) {
            return new Info(0, 0);
        }
        Info left = processCBT(node.left);
        Info right = processCBT(node.right);
        int height = Math.max(left.height, right.height) + 1;
        int nodes = left.nodes + right.nodes + 1;
        return new Info(height, nodes);
    }

    public static class ReturnType {
        public int height;
        public boolean isBalanced;

        public ReturnType(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static boolean isBalanced(Node head) {
         return processIsBalanced(head).isBalanced;
    }

    public static ReturnType processIsBalanced(Node node) {
        if (node == null) {
            return new ReturnType(0, true);
        }
        ReturnType left = processIsBalanced(node.left);
        ReturnType right = processIsBalanced(node.right);
        int height = left.height + right.height + 1;
        boolean balanced = left.isBalanced && right.isBalanced
                            && Math.abs(left.height - right.height) < 2;
        return new ReturnType(height, balanced);
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
    
    public static Node lca(Node head, Node o1, Node o2) {
        HashMap<Node, Node> fatherMap = new HashMap<>();
        fatherMap.put(head, head);
        lcaProcess(head, fatherMap);
        HashSet<Node> set = new HashSet<>();
        Node cur = o1;
        while (cur != fatherMap.get(cur)) {
            set.add(cur);
            cur = fatherMap.get(cur);
        }
        set.add(head);

        cur = o2;
        while (!set.contains(cur)) {
            cur = fatherMap.get(cur);
        }
        return cur;
    }
    
    public static void lcaProcess(Node head, HashMap<Node, Node> fatherMap) {
        if (head == null) {
            return;
        }
        fatherMap.put(head.left, head);
        fatherMap.put(head.right, head);
        lcaProcess(head.left, fatherMap);
        lcaProcess(head.right, fatherMap);
    }

    public static Node lowestAncestor(Node head, Node o1, Node o2) {
        if (head == null || head == o1 || head == o2) {
            return head;
        }
        Node left = lowestAncestor(head.left, o1, o2);
        Node right = lowestAncestor(head.right, o1, o2);
        if (left != null && right != null) {
            return head;
        }
        return left != null ? left : right;
    }

    public static class NodeWithParent {
        public int value;
        public NodeWithParent left;
        public NodeWithParent right;
        public NodeWithParent parent;

        public NodeWithParent(int value) {
            this.value = value;
        }
    }

    public static NodeWithParent getSuccessorNode(NodeWithParent node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getSuccessorNode(node.right);
        }else {
            NodeWithParent parent = node.parent;
            while (parent != null && parent.left != node) {
                node = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    public static Node getleftMost(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static String serialByPre(Node head) {
        if (head == null) {
            return "#_";
        }
        String value = head.value + "_";
        value += serialByPre(head.left);
        value += serialByPre(head.right);
        return value;
    }

    public static Node reconByPreString(String preStr) {
        if (preStr == null) {
            return null;
        }
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.add(values[i]);
        }
        return reconProOrder(queue);
    }

    public static Node reconProOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node node = new Node(Integer.valueOf(value));
        node.left = reconProOrder(queue);
        node.right = reconProOrder(queue);
        return node;
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.print("pre-order:");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order:");
        inOrderRecur(head);
        System.out.println();
        System.out.print("post-order:");
        postOrderRecur(head);
        System.out.println();

        System.out.println();
        System.out.print("pre-order:");
        preOrderUnRecur(head);
        System.out.print("in-order:");
        inOrderUnRecur(head);
        System.out.print("post-order:");
        postOrderUnRecur(head);

        System.out.println("**********1**********" );

        head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);

        System.out.println(getMaxWidth(head));
        System.out.println(getMaxWidth2(head));

        System.out.println("**********2**********" );

        System.out.println(checkBST(head));
        System.out.println(checkBST2(head));

        System.out.println("**********3**********" );


    }
    

}
