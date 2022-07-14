/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reverselinkedlist;

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
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return head;
        
        ListNode prev = head;
        ListNode current = prev.next;
        ListNode next = current.next;
        prev.next = null;
        while(current != null){
            current.next = prev;
            prev = current;
            current = next;
            if(current != null)
                next = current.next;
        }
        head = prev;
        return head;
    }
}
public class ReverseLinkedList {

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
        
        head.printNodes();
        head = solution.reverseList(head);
        head.printNodes();
    }
    
}
