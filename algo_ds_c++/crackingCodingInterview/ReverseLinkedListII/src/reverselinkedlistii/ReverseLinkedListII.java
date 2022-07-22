/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reverselinkedlistii;

/**
 *
 * @author youssef hany
 */
// Definition for singly-linked list.
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

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null || left == right) return head;
        ListNode p1 = head, p2 = this.moveNStepsToTheRight(head, left == 1 ? (right-left) : (right-left+1));
        int steps = left-2;
        while(steps>0){
            p1 = p1.next;
            p2 = p2.next;
            steps--;
        }
        ListNode reversedList = this.reverseList(left == 1 ? p1 : p1.next,p2.next,right-left);
        if(left == 1) head = reversedList;
        else p1.next = reversedList;
        return head;
    }
    
    private ListNode moveNStepsToTheRight(ListNode head, int steps){
        ListNode temp = head;
        while(steps > 0){
            temp = temp.next;
            steps--;
        }
        return  temp;
    }
    
    private ListNode reverseList(ListNode head, ListNode tail, int operations){
        if(head == null) return head;
        ListNode prev = head;
        ListNode current = head.next;
        ListNode next = head.next == null ? null : head.next.next;
        prev.next = tail;
        while(operations>0){
            current.next = prev;
            prev = current;
            current = next;
            next = next == null ? null : next.next;
            operations--;
        }
        head = prev;
        return head;
    }
}

public class ReverseLinkedListII {

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
        
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        
        l1.printList();
        l1 = solution.reverseBetween(l1, 1, 5);
        l1.printList();
    }

}
