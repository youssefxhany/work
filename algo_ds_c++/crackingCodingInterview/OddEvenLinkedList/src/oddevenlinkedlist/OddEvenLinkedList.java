/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oddevenlinkedlist;

/**
 *
 * @author youssef hany
 */
//Definition for singly-linked list.
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
        while(temp!=null){
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println("");
    }
}

class Solution {
    private int size = 0;
    
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return head;
        System.out.println("here");
        ListNode lastNode = this.getLastNode(head);
        ListNode odd = head, even = head.next;
        int i =0;
        while(i < this.size/2){
            odd.next = even.next;
            even.next = null;
            lastNode.next = even;
            lastNode = even;
            odd = odd == null ? null : odd.next;
            even = odd == null ? null : odd.next;
            i++;
        }
        return head;
    }
    
    private ListNode getLastNode(ListNode head){
        ListNode temp = head;
        ListNode lastNode = null;
        while(temp!=null){
            size++;
            if(temp.next==null)
                lastNode = temp;
            temp =temp.next;
        }
        return lastNode;
    }
}
public class OddEvenLinkedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(7);
    
        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next = l5;
//        l5.next = l6;
//        l6.next = l7;
        
        l1.printList();
        l1 = solution.oddEvenList(l1);
        l1.printList();
    }
}
