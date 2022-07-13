/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kthtolast;

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

    public ListNode getKthToLast(ListNode head, int k) {
        if(head == null)
            return null;
        
        ListNode pointer1 = head;
        ListNode pointer2 = this.allocatePointer(head, k);
        
        if(pointer2 == null)
            return head;
        
        while(pointer2.next != null){
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        
        return pointer1.next;
    }
    
    private ListNode allocatePointer(ListNode head,int k){
        ListNode pointer1 = head;
        while(k>0){
            pointer1 = pointer1.next;
            if(pointer1 == null)
                return null;
            k--;
        }
        return pointer1;
    }
}

public class KthToLast {

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
        head.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n9;
        
        head.printNodes();
        Solution solution = new Solution();
        System.out.println(solution.getKthToLast(head, 8).val);
    }

}
