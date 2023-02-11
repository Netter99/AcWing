package 代码随想录;

public class day1 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(){}

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }


    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(a[0]);
        ListNode pre = head;
        for (int i = 1; i < a.length; i++) {
            ListNode temp = new ListNode(a[i]);
            pre.next = temp;
            pre = temp;
        }
        pre = head;
        while (pre != null) {
            System.out.print(pre.val + " ");
            pre = pre.next;
        }
        System.out.println();
        pre = reverseList(head);
        while (pre != null) {
            System.out.print(pre.val + " ");
            pre = pre.next;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */