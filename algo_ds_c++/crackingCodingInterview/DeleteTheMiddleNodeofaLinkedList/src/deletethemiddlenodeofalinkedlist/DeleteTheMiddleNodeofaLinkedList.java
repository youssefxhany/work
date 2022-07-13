/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deletethemiddlenodeofalinkedlist;

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
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    void printNodes() {
        ListNode temp = this;
        if (temp == null) {
            System.out.println("EMPTY LIST");
        }
        while (temp != null) {
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println("");
    }
}

class Solution {

    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            head.next = null;
            return head;
        }

        ListNode pointer1 = head, pointer2 = head.next.next;
        boolean flag = false;
        while (pointer2 != null && pointer2.next != null) {
            pointer1 = pointer1.next;
            pointer2 = pointer2.next.next;
        }
        pointer1.next = pointer1.next.next;
        return head;
    }
}

public class DeleteTheMiddleNodeofaLinkedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode head = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(8);
        ListNode n4 = new ListNode(9);
        ListNode n5 = new ListNode(10);
        ListNode n6 = new ListNode(11);
        ListNode n7 = new ListNode(5);
        ListNode n8 = new ListNode(6);
        ListNode n9 = new ListNode(7);
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;

        while (head != null) {
            head.printNodes();
            head = solution.deleteMiddle(head);
            head.printNodes();
            System.out.println("---------------------");
        }

    }

}
