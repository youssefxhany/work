/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindromelinkedlist;

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
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null)
            return true;
        if(head.next.next == null)
            return head.val == head.next.val;
        ListNode list1Node = head;
        ListNode list2Node = this.reverseLinkedList(this.findMiddleNode(head));
        while(list1Node != null && list2Node != null){
            if(list1Node.val != list2Node.val)
                return false;
            list1Node = list1Node.next;
            list2Node = list2Node.next;
        }
        return true;
    }
    
    private ListNode findMiddleNode(ListNode head){
        ListNode pointer1 = head;
        ListNode pointer2 = head.next.next;
        while(pointer2 != null && pointer2.next != null){
            pointer1 = pointer1.next;
            pointer2 = pointer2.next.next;
        }
        return pointer1.next;
    }
    
    private ListNode reverseLinkedList(ListNode head){
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
public class PalindromeLinkedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode head = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(4);
        ListNode n7 = new ListNode(3);
        ListNode n8 = new ListNode(2);
        ListNode n9 = new ListNode(1);
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        
        head.printNodes();
        System.out.println(solution.isPalindrome(head));
    }
    
}
