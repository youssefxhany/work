/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swappingnodesinalinkedlist;

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

    private ListNode lastNode;
    
    public ListNode swapNodes(ListNode head, int k) {
        ListNode kFromStart = head;
        ListNode KFromEnd = head;
        ListNode guide = this.allocatePointer(head, k);

        //swap 1st and last
        if (guide == null) {
            this.swap(kFromStart, this.lastNode);
            return head;
        }

        while (guide.next != null || (k-1) > 0) {
            if(k-1 > 0){
                kFromStart = kFromStart.next;
                k--;
            }
            if (guide.next != null) {
                KFromEnd = KFromEnd.next;
                guide = guide.next;
            }
        }
        
        this.swap(kFromStart, KFromEnd.next);
        return head;
    }

    private ListNode allocatePointer(ListNode head, int n) {
        ListNode pointer = head;
        while (n > 0) {
            if(pointer != null && pointer.next == null){
                this.lastNode = pointer;
            }
            pointer = pointer.next;
            if (pointer == null) {
                return null;
            }
            n--;
        }
        return pointer;
    }

    private void swap(ListNode p1, ListNode p2) {
        if(p1 == p2 || p1.val == p2.val)
            return;
        int temp = p1.val;
        p1.val = p2.val;
        p2.val = temp;
    }
}

public class SwappingNodesInaLinkedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode head = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(8);
        ListNode n4 = new ListNode(9);
        ListNode n5 = new ListNode(10);
        ListNode n6 = new ListNode(11);
        ListNode n7 = new ListNode(5);
        ListNode n8 = new ListNode(6);
        ListNode n9 = new ListNode(7);
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;

        head.printNodes();
        head = solution.swapNodes(head, 1);
        head.printNodes();

    }

}
