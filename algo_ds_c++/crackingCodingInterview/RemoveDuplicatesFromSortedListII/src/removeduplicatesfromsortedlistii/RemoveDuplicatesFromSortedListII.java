/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package removeduplicatesfromsortedlistii;

/**
 *
 * @author youssef hany
 */
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    public void printList(){
        ListNode temp = this;
        while(temp!=null){
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println("");
    }
}
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode start = head, end = head, before = null;
        while(start != null && end != null){
            if(end.next == null || start.val != end.next.val){
                if(before == null && start != end){
                    head = end.next;
                    start = end = head;
                }else if(start == end){
                    before = start;
                    start = end = end.next;
                }else{
                    before.next = end.next;
                    start = end = end.next;
                }
                continue;
            }
            end = end.next;
        }
        return head;
    }
}
public class RemoveDuplicatesFromSortedListII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(3);
        ListNode l6 = new ListNode(3);
        ListNode l7 = new ListNode(6);
        ListNode l8 = new ListNode(7);
        ListNode l9 = new ListNode(7);
        ListNode l10 = new ListNode(7);
        ListNode l11 = new ListNode(8);
        ListNode l12 = new ListNode(8);
        
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;
        l9.next = l10;
        l10.next  = l11;
        l11.next = l12;
        
        l1.printList();
        l1 = solution.deleteDuplicates(l1);
        l1.printList();
    }
    
}
