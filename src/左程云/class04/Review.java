package 左程云.class04;

import java.util.Stack;

/**
 * @description:
 * @author: Netter
 * @date: 2023-02-04 12:19
 */
public class Review {

    public static void printLink(Node head) {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static Node createNoHeadLink(int[] arr) {
        Node head = new Node(arr[0]);
        Node cur = head;
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            cur.next = temp;
            cur = temp;
        }
        return head;
    }

    public static class Node {
        public int value;
        public Node next;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static Node singleReverse1(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head, pre = null;
        Node next = cur.next;
        while (next != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = cur.next;
        }
        cur.next = pre;
        return cur;
    }

    public static Node singleReverse2(Node head) {
        if (head == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }

        Node pre = stack.pop();
        head = pre;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            pre.next = cur;
            pre = cur;
        }
        pre.next = null;
        return head;
    }

    public static void findPublic(Node head1, Node head2) {
        Node p1 = head1;
        Node p2 = head2;
        System.out.print("共同部分：");
        while (p1 != null && p2 != null) {
            if (p1.value == p2.value) {
                System.out.print(p1.value + " ");
                p1 = p1.next;
                p2 = p2.next;
            }else if (p1.value < p2.value) {
                p1 = p1.next;
            }else {
                p2 = p2.next;
            }
        }
        System.out.println();
    }

    public static boolean isPalindrome1(Node head) {
        if (head == null) {
            return true;
        }

        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            System.out.println(slow.value + "-" + fast.value);
            slow = slow.next;
            fast = fast.next.next;
        }

        Node cur = slow.next;

        Stack<Node> stack = new Stack<>();
        while (cur != null) {
            stack.add(cur);
            cur = cur.next;
        }
        cur = head;
        while (!stack.isEmpty()) {
            if (cur.value != stack.pop().value) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }

    public static boolean isPalindrome2(Node head) {
        if (head == null) {
            return true;
        }

        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            //System.out.println(slow.value + "-" + fast.value);
            slow = slow.next;
            fast = fast.next.next;
        }

        // 需要逆转的头结点
        Node tempHead = slow.next;
        // 判断终止条件
        slow.next = null;
        Node pre = slow;
        while (tempHead != null) {
            Node cur = tempHead.next;
            tempHead.next = pre;
            pre = tempHead;
            tempHead = cur;
        }

        tempHead = pre;
        slow = head;
        boolean res = true;
        while (slow != null) {
            if (slow.value != tempHead.value) {
                res = false;
                break;
            }
            slow = slow.next;
            tempHead = tempHead.next;
        }

        tempHead = null;
        while (pre != null) {
            Node cur = pre.next;
            pre.next = tempHead;
            tempHead = pre;
            pre = cur;
        }
        return res;
    }

    public static Node divideThree(Node head, int value) {
        Node sH = null, sT = null;
        Node eH = null, eT = null;
        Node bH = null, bT = null;
        Node cur = head, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = null;
            if (cur.value < value) {
                if (sH == null) {
                    sH = cur;
                    sT = cur;
                }else {
                    sT.next = cur;
                    sT = cur;
                }
            }else if (cur.value == value) {
                if (eH == null) {
                    eH = cur;
                    eT = cur;
                }else {
                    eT.next = cur;
                    eT = cur;
                }
            }else {
                if (bH == null) {
                    bH = cur;
                    bT = cur;
                }else {
                    bT.next = cur;
                    bT = cur;
                }
            }
            cur = next;
        }

        if (sH != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        if (eH != null) {
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop1) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else {
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return cur1;
                }
                cur1 = cur1.next;
            }
        }
        return null;
    }

    public static Node noLoop(Node head1, Node head2) {
        Node p1 = head1;
        Node p2 = head2;
        int n = 0;
        while (p1.next != null) {
            n++;
            p1 = p1.next;
        }
        while (p2.next != null) {
            n--;
            p2 = p2.next;
        }

        // 两个链表最后的结点不同，说明不相交
        if (p1 != p2) {
            return null;
        }

        p1 = n > 0 ? head1 : head2;
        p2 = p1 == head1 ? head2 : head1;
        n = Math.abs(n);

        while (n-- > 0) {
            p1 = p1.next;
        }

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next, fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        Node head = createNoHeadLink(arr);

        System.out.println("单链表-无头结点-反转");
        System.out.print("反转前：");
        printLink(head);
        Node cur = singleReverse2(head);
        System.out.print("反转后：");
        printLink(cur);

        System.out.println("=============1=============");

        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{2, 5, 6};
        Node head1 = new Node(arr1[0]);
        cur = head1;
        for (int i = 1; i < arr1.length; i++) {
            Node temp = new Node(arr1[i]);
            cur.next = temp;
            cur = temp;
        }
        Node head2 = new Node(arr2[0]);
        cur = head2;
        for (int i = 1; i < arr2.length; i++) {
            Node temp = new Node(arr2[i]);
            cur.next = temp;
            cur = temp;
        }
        System.out.print("head1:");
        printLink(head1);
        System.out.print("head2:");
        printLink(head2);
        findPublic(head1, head2);

        System.out.println("=============2=============");


        arr = new int[]{1, 2, 3, 3, 2, 1};
        head = createNoHeadLink(arr);
        printLink(head);
        System.out.println("是否是回文？ " + (isPalindrome1(head) ? "Yes" : "No"));
        System.out.println("是否是回文？ " + (isPalindrome2(head) ? "Yes" : "No"));

        System.out.println("=============3=============");


        arr = new int[]{4, 6, 3, 5, 8, 5, 2};
        head = createNoHeadLink(arr);
        printLink(head);
        head = divideThree(head, 5);
        printLink(head);

        System.out.println("=============4=============");
    }



}
