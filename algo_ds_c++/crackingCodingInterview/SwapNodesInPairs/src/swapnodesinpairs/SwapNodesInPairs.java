/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swapnodesinpairs;

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
    
    public void printList(){
        ListNode temp = this;
        while(temp != null){
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println("");
    }
}
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode p1 = head, p2 = head.next;
        head = reverseHead(p1, p2);
        p2 = p1.next == null ? null : p1.next.next;
        while(p2 != null){
            p1 = this.swapAdjacentPairs(p1, p2);
            p2 = p1 == null || p1.next == null ? null : p1.next.next;
        }
        return head;
    }
    
    private ListNode reverseHead(ListNode p1, ListNode p2){
        p1.next = p2.next;
        p2.next = p1;
        return p2;
    }
    
    private ListNode swapAdjacentPairs(ListNode prev, ListNode node2){
        if(prev == null || node2 == null) return null;
        ListNode node1 = prev.next, next = node2.next;
        node2.next = node1;
        node1.next = next;
        prev.next = node2;
        return node1;
    }
}
public class SwapNodesInPairs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        ListNode l8 = new ListNode(8);
        ListNode l9 = new ListNode(9);
        ListNode l10 = new ListNode(10);
        ListNode l11 = new ListNode(11);
        
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;
        l9.next = l10;
        l10.next = l11;
        
        l1.printList();
        l1 = solution.swapPairs(l1);
        l1.printList();
    }
    
}
