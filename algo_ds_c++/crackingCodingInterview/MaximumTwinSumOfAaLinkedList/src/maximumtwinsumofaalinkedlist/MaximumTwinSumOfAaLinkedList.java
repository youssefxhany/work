/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maximumtwinsumofaalinkedlist;

/**
 *
 * @author youssef hany
 */

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    public void printList(){
        ListNode temp = this;
        while(temp!= null){
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println("");
    }
}
class Solution {
    public int pairSum(ListNode head) {
        int maxTwinSum = 0;
        if(head == null || head.next == null) return maxTwinSum;
        int size = this.getListSize(head);
        ListNode slowRunner = head, fastRunner = head.next.next;
        while(fastRunner != null && fastRunner.next != null){
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
        }
        ListNode p1 = head;
        ListNode p2 = this.reverseList(slowRunner.next);
        while(p1 != null && p2 != null){
            maxTwinSum = (p1.val + p2.val) > maxTwinSum ? (p1.val + p2.val) : maxTwinSum;
            p1 = p1.next;
            p2 = p2.next;
        }
        return maxTwinSum;
    }
    
    private int getListSize(ListNode head){
        ListNode temp = head;
        int size =0;
        while(temp != null){
            size++;
            temp = temp.next;
        }
        return size;
    }
    
    private ListNode reverseList(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode prev = head;
        ListNode current = head.next;
        ListNode next = head.next == null ? null : head.next.next;
        prev.next = null;
        while(current != null){
            current.next = prev;
            prev = current;
            current = next;
            next = next == null ? null : next.next;
        }
        head = prev;
        return head;
    }
}
public class MaximumTwinSumOfAaLinkedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(8);
        ListNode l6 = new ListNode(7);
        ListNode l7 = new ListNode(9);
        ListNode l8 = new ListNode(7);
        
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        
        l1.printList(); 
        System.out.println(solution.pairSum(l1));
    }
    
}
