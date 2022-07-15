/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deletennodesaftermnodesofalinkedlist;

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
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode pointer = head;
        while(pointer!=null){
            pointer = this.moveM(pointer, m-1);
            if(pointer == null) return head;
            pointer = this.deleteN(pointer, n);
        }
        return head;
    }
    
    private ListNode moveM(ListNode pointer, int m){
        while(m>0 && pointer != null){
            pointer = pointer.next;
            m--;
        }
        return pointer;
    }
    
    private ListNode deleteN(ListNode pointer, int n){
        if(pointer.next == null) return null;
        while(n > 0){
            if(pointer.next == null) return null;
            pointer.next = pointer.next.next;
            if(pointer == null) return null;
            n--;
        }
        pointer = pointer.next;
        return pointer;
    }
}
public class DeleteNNodesAfterMNodesofaLinkedList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode head = new ListNode(9);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(7);
        ListNode n4 = new ListNode(7);
        ListNode n5 = new ListNode(9);
        ListNode n6 = new ListNode(10);
        ListNode n7 = new ListNode(8);
        ListNode n8 = new ListNode(2);
        ListNode n9 = new ListNode(9);
        ListNode n10 = new ListNode(10);
        ListNode n11 = new ListNode(11);
        ListNode n12 = new ListNode(12);
        ListNode n13 = new ListNode(13);
        
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
//        n8.next = n9;
//        n9.next = n10;
//        n10.next = n11;
//        n11.next = n12;
//        n12.next = n13;
        
        head.printNodes();
        head = solution.deleteNodes(head, 1, 2);
        head.printNodes();
    }
    
}
