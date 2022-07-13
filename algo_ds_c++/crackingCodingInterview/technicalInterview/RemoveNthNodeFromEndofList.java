/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package removenthnodefromendoflist;

/**
 *
 * @author youssef hany
 */
/**
 * Definition for singly-linked list.
 **/
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null){
            return null;
        }
        
        ListNode pointer1, pointer2;
        pointer1 = head;
        pointer2 = this.allocatePointer2(head, n);
        
        //we need to remove 1st node/head
        if(pointer2 == null){
            head = pointer1.next;
            return head;
        }
        
        while(pointer2.next != null){
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        
        //remove pointer1.next
        pointer1.next = pointer1.next.next;
        return head;
    }
    
    private ListNode allocatePointer2(ListNode head, int n){
        ListNode pointer2 = head;
        while(n>0){
            pointer2 = pointer2.next;
            if(pointer2 == null)
                return null;
            n--;
        }
        return pointer2;
    }
}
public class RemoveNthNodeFromEndofList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(3);
        ListNode n6 = new ListNode(2);
        ListNode n7 = new ListNode(4);
        ListNode n8 = new ListNode(4);
        ListNode n9 = new ListNode(4);
//        head.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
//        n5.next = n6;
//        n6.next = n7;
//        n7.next = n8;
//        n8.next = n9;
        
        head.printNodes();
        Solution solution = new Solution();
        head = solution.removeNthFromEnd(head, 1);
        head.printNodes();
    }
    
}
