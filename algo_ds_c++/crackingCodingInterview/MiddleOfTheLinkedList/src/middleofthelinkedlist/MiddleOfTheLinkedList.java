package middleofthelinkedlist;

import java.util.List;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    public void printList(){
        ListNode temp = this;
        while (temp != null){
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println();
    }
 }

class Solution {
    public ListNode middleNode(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slowRunner = head;
        ListNode fastRunner = head.next.next;
        while(fastRunner != null && fastRunner.next != null){
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
        }
        return slowRunner.next;
    }
}
public class MiddleOfTheLinkedList {

    public static void main(String[] args) {
	// write your code here
    }
}