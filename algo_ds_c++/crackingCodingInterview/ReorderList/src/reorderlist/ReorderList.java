/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorderlist;

/**
 *
 * @author youssef hany
 */
class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void PrintList() {
        ListNode temp = this;
        while (temp != null) {
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println();
    }
}

class Solution {

    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode middleNode = this.getMiddle(head);
        ListNode otherHead = this.reverseList(middleNode.next);
        middleNode.next = null;
        ListNode p1 = head, p2 = head.next, p3 = otherHead, p4 = otherHead.next;
        while (p1 != null && p3 != null) {
            p1.next = p3;
            p3.next = p2;
            p1 = p2;
            p3 = p4;
            p2 = p2 == null ? null : p2.next;
            p4 = p4 == null ? null : p4.next;
        }
        return;
    }

    private ListNode getMiddle(ListNode head) {
        ListNode slowRunner = head, fastRunner = head;
        while (fastRunner != null && fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
        }
        return slowRunner;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = head;
        ListNode current = head.next;
        ListNode next = head.next.next;
        prev.next = null;
        while (current != null) {
            current.next = prev;
            prev = current;
            current = next;
            if (current == null) {
                break;
            }
            next = next.next;
        }
        head = prev;
        return head;
    }
}

public class ReorderList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l13 = new ListNode(3);
        ListNode l14 = new ListNode(4);
        ListNode l15 = new ListNode(5);
        ListNode l16 = new ListNode(6);
        ListNode l17 = new ListNode(7);
        ListNode l18 = new ListNode(8);

        l1.next = l12;
        l12.next = l13;
        l13.next = l14;
        l14.next = l15;
        l15.next = l16;
        l16.next = l17;
        l17.next = l18;

        l1.PrintList();

        solution.reorderList(l1);

        l1.PrintList();
    }

}
