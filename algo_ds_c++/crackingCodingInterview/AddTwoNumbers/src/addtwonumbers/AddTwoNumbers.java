/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addtwonumbers;

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
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null)
            return (l1 == l2) ? null : l1 != null ? l1 : l2;
        int extra = 0;
        ListNode newHead = null;
        ListNode headPointer = null;
        while(l1 != null || l2 != null){
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int digit = (num1 + num2 + extra) % 10;
            ListNode newNode = new ListNode(digit);
            extra = (num1 + num2 + extra) / 10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            if(newHead == null){
                newHead = headPointer = newNode;
                continue;
            }
            headPointer.next = newNode;
            headPointer = headPointer.next;
        }
        if(extra != 0){
            ListNode newNode = new ListNode(extra);
            headPointer.next = newNode;
        }
        return newHead;
    } 
}
public class AddTwoNumbers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode head1 = new ListNode(7);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(6);
        head1.next = n2;
        n2.next = n3;
        
        ListNode head2 = new ListNode(5);
        ListNode n4 = new ListNode(9);
        ListNode n5 = new ListNode(2);
        ListNode n6 = new ListNode(8);
        head2.next = n4;
        n4.next = n5;
        n5.next = n6;
        
        head1.printNodes();
        head2.printNodes();
        
        ListNode newList = solution.addTwoNumbers(head1, head2);
        newList.printNodes();
    }
    
}
