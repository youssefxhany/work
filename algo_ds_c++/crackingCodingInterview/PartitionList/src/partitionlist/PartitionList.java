/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitionlist;

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
    private int lenght = 1;
    public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null) return head;
        ListNode lastNode = this.getLast(head);
        ListNode prev = null, current = head , next = head.next;
        ListNode tempLast = lastNode;
        System.out.println(this.lenght);
        while(this.lenght > 0){
            this.lenght--;
            if(current.val >= x && current.next != null){
                if(current == head){
                    current.next = null;
                    tempLast.next = current;
                    tempLast = tempLast.next;
                    head = next;
                    current = head;
                    next = next == null ? null : next.next;
                }else{
                    prev.next = next;
                    current.next = null;
                    tempLast.next = current;
                    tempLast = tempLast.next;
                    current = prev.next;
                    next = next == null ? null : next.next;
                }
                continue;
            }
            prev = current;
            current = next;
            next = next == null ? null : next.next;
        }
        return head;
    }
    
    private ListNode swap(ListNode head, int x){
        ListNode temp = head;
        head = head.next;
        head.next = temp;
        temp.next = null;
        return head;
    }
    
    private ListNode getLast(ListNode head){
        ListNode temp = head;
        while(temp.next != null){
            this.lenght++;
            temp = temp.next;
        }
        return temp;
    }
}
public class PartitionList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(0);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(2);
        ListNode n7 = new ListNode(2);
        ListNode n8 = new ListNode(1);
        ListNode n9 = new ListNode(0);
        
        head.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
//        n5.next = n6;
//        n6.next = n7;
//        n7.next = n8;
//        n8.next = n9;
        
        head.printNodes();
        
        head = solution.partition(head, 2);
        
        head.printNodes();
    }
    
}
