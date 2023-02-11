package 左程云.class04;

import java.security.Signature;
import java.util.HashMap;
import java.util.Stack;

/**
 * @description:
 * @author: Netter
 * @date: 2023-01-31 13:39
 */
public class Link {

    public static void printLink(SingleNode head) {
        SingleNode cur = head;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static SingleNode createSingleLink_NoHead(int[] arr) {
        SingleNode head = new SingleNode(arr[0]);
        SingleNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            SingleNode temp = new SingleNode(arr[i]);
            cur.next = temp;
            cur = temp;
        }
        return head;
    }

    public static class SingleNode {
        public int value;
        public SingleNode next;

        public SingleNode() {
        }

        public SingleNode(int value) {
            this.value = value;
        }
    }

    public static class RandNode {
        public int value;
        public RandNode next;
        public RandNode rand;

        public RandNode() {
        }

        public RandNode(int value) {
            this.value = value;
        }
    }

    // 单链表-无头结点-反转
    public static SingleNode singleReverse1(SingleNode head) {
        if (head == null) {
            return head;
        }
        SingleNode cur = head, temp = cur.next;
        cur.next = null;
        while (temp != null) {
            SingleNode node = temp.next;
            temp.next = cur;
            cur = temp;
            temp = node;
        }
        return cur;
    }

    // 单链表-有头结点-反转
    public static SingleNode singleReverse2(SingleNode head) {
        SingleNode pre = head, cur = head.next, temp = cur.next;
        cur.next = null;
        while (temp != null) {
            pre.next = temp;
            SingleNode node = temp.next;
            temp.next = cur;
            cur = temp;
            temp = node;
        }
        return head;
    }

    //寻找有序链表的公共部分
    public static void findPublic(SingleNode head1, SingleNode head2) {
        SingleNode p1 = head1, p2 = head2;
        System.out.print("共同部分：");
        while (p1 != null && p2 != null) {
            if (p1.value == p2.value) {
                System.out.print(p1.value + " ");
                p1 = p1.next;
                p2 = p2.next;
            } else if (p1.value < p2.value) {
                p1 = p1.next;
            } else {
                p2 = p2.next;
            }
        }
        System.out.println();
    }

    // 判断链表是否是回文 --- 栈
    public static boolean isPalindrome1(SingleNode head) {
        //简单使用
        //Stack<Integer> stack = new Stack<>();
        //SingleNode cur = head;
        //while (cur != null) {
        //    stack.push(cur.value);
        //    cur = cur.next;
        //}
        //
        //cur = head;
        //boolean check = true;
        //while (cur != null) {
        //    if(stack.pop() != cur.value) {
        //        check = false;
        //        break;
        //    }
        //    cur = cur.next;
        //}
        //return check;

        //优化 --- 只保存一半
        Stack<Integer> stack = new Stack<>();
        SingleNode cur = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            cur = cur.next;
            fast = fast.next.next;
        }
        cur = cur.next;
        while (cur != null) {
            stack.add(cur.value);
            cur = cur.next;
        }
        cur = head;
        boolean check = true;
        while (!stack.isEmpty()) {
            if(stack.pop() != cur.value) {
                check = false;
                break;
            }
            cur = cur.next;
        }
        return check;
    }

    // 判断链表是否是回文 --- 快慢指针
    public static boolean isPalindrome2(SingleNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        SingleNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        SingleNode tempHead = slow.next;
        /** 这里不需要理会奇偶情况，原因：
         *      以head作为头进行遍历，并作为退出循环的条件即可
         */

        slow.next = null;
        SingleNode pre = slow;
        while (tempHead != null) {
            SingleNode tempNext = tempHead.next;
            tempHead.next = pre;
            pre = tempHead;
            tempHead = tempNext;
        }

        tempHead = pre;
        SingleNode cur = head;
        boolean res = true;
        while (cur != null) {
            if (cur.value != tempHead.value) {
                res = false;
                break;
            }
            cur = cur.next;
            tempHead = tempHead.next;
        }

        tempHead = null;
        while (pre != null) {
            SingleNode tempNext = pre.next;
            pre.next = tempHead;
            tempHead = pre;
            pre = tempNext;
        }

        return res;
    }

    // 将链表按< = > value值，进行重组
    public static SingleNode divideThree(SingleNode head, int value) {
        SingleNode sH = null;
        SingleNode sT = null;
        SingleNode eH = null;
        SingleNode eT = null;
        SingleNode bH = null;
        SingleNode bT = null;
        SingleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if(head.value < value) {
                if(sH == null) {
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if(head.value == value) {
                if(eH == null) {
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if(bH == null) {
                    bH = head;
                    bT = head;
                }else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
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

    // 复制携带随机指针的链表 --- 哈希表
    public static RandNode copyListWithRand1(RandNode head) {
        HashMap<RandNode, RandNode> map = new HashMap<>();
        RandNode cur = head;
        while (cur != null) {
            map.put(cur, new RandNode(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = cur.next;
            map.get(cur).rand = cur.rand;
            cur = cur.next;
        }
        return map.get(head);
    }

    // 复制携带随机指针的链表 --- 将新结点加入到对应结点的后面
    public static RandNode copyListWithRand2(RandNode head) {
        if (head == null) {
            return head;
        }
        RandNode cur = head;
        RandNode next = null;
        // 1 -> 1' -> 2
        while (cur != null) {
            next = cur.next;
            cur.next = new RandNode(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        RandNode curCopy = null;
        // set copy node rand
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            // cur.rand不为null，说明是原链表结点！！！
            curCopy.rand = cur.rand != null ? cur.rand : null;
            cur = next;
        }
        RandNode res = head.next;
        cur = head;
        // split
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return  res;
    }

    // 找到链表第一个入环节点，如果无环,返回null
    public static SingleNode getIntersectNode(SingleNode head1, SingleNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        SingleNode loop1 = getLoopNode(head1);
        SingleNode loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }


    public static SingleNode getLoopNode(SingleNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // 因为起始位置都是head，为了进入循环，先走
        SingleNode n1 = head.next;  // n1 -> slow
        SingleNode n2 = head.next.next;  // n2 -> fast
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }

        n2 = head;
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    public static SingleNode noLoop(SingleNode head1, SingleNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        SingleNode cur1 = head1;
        SingleNode cur2 = head2;
        // 用一个变量比较两个长度
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;   // 谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1;   // 谁短，谁的头变成cur2
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
    }

    // 两个有环链表，返回第一个相交节点，如果不相交返回null
    public static SingleNode bothLoop(SingleNode head1, SingleNode loop1, SingleNode head2, SingleNode loop2) {
        SingleNode cur1 = null;
        SingleNode cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != null) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != null) {
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
            return null;
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        SingleNode head = new SingleNode(arr[0]);
        SingleNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            SingleNode temp = new SingleNode(arr[i]);
            cur.next = temp;
            cur = temp;
        }

        System.out.println("单链表-无头结点-反转");
        System.out.print("反转前：");
        printLink(head);
        head = singleReverse1(head);
        cur = head;
        System.out.print("反转后：");
        printLink(head);

        System.out.println("=============1=============");

        head = new SingleNode(-1);
        cur = head;
        for (int i = 0; i < arr.length; i++) {
            SingleNode temp = new SingleNode(arr[i]);
            cur.next = temp;
            cur = temp;
        }
        System.out.println("单链表-有头结点-反转");
        System.out.print("反转前：");
        printLink(head);
        head = singleReverse2(head);
        cur = head;
        System.out.print("反转后：");
        printLink(head);

        System.out.println("=============2=============");

        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{2, 5, 6};
        SingleNode head1 = new SingleNode(arr1[0]);
        cur = head1;
        for (int i = 1; i < arr1.length; i++) {
            SingleNode temp = new SingleNode(arr1[i]);
            cur.next = temp;
            cur = temp;
        }
        SingleNode head2 = new SingleNode(arr2[0]);
        cur = head2;
        for (int i = 1; i < arr2.length; i++) {
            SingleNode temp = new SingleNode(arr2[i]);
            cur.next = temp;
            cur = temp;
        }
        System.out.print("head1:");
        printLink(head1);
        System.out.print("head2:");
        printLink(head2);
        findPublic(head1, head2);

        System.out.println("=============3=============");

        arr = new int[]{1, 2, 3, 3, 2, 1};
        head = createSingleLink_NoHead(arr);
        printLink(head);
        System.out.println("是否是回文？ " + (isPalindrome2(head) ? "Yes" : "No"));

        arr = new int[]{1, 2, 3, 2, 1};
        head = createSingleLink_NoHead(arr);
        printLink(head);
        System.out.println("是否是回文？ " + (isPalindrome2(head) ? "Yes" : "No"));

        arr = new int[]{1, 2, 3, 2};
        head = createSingleLink_NoHead(arr);
        printLink(head);
        System.out.println("是否是回文？ " + (isPalindrome2(head) ? "Yes" : "No"));

        System.out.println("=============4=============");

        arr = new int[]{4, 6, 3, 5, 8, 5, 2};
        head = createSingleLink_NoHead(arr);
        printLink(head);
        head = divideThree(head, 5);
        printLink(head);

    }

}
