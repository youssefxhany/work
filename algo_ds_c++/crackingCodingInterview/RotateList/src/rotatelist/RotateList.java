/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotatelist;

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
        while(temp!=null){
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println("");
    }
}
class Solution {
    private ListNode lastNode = null;
    
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;
        int size = this.getListSize(head);
        k = k % size;
        if(k == 0) return head;
        ListNode p1 = head, p2 = this.moveToTheRight(head, k);
        while(p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        ListNode newHead = p1.next;
        p1.next = null;
        lastNode.next = head;
        return newHead;
    }
    
    private int getListSize(ListNode head){
        ListNode temp = head;
        int size = 0;
        while(temp != null){
            temp = temp.next;
            if(temp != null && temp.next == null){
                lastNode = temp;
            }
            size++;
        }
        return size;
    }
    
    private ListNode moveToTheRight(ListNode head, int steps){
        ListNode temp = head;
        while(steps > 0){
            temp = temp.next;
            steps--;
        }
        return temp;
    }
}
public class RotateList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
}
