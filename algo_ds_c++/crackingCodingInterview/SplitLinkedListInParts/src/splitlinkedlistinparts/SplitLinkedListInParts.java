/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitlinkedlistinparts;

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
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode result[] = new ListNode[k];
        int size = this.getListSize(head);
        int moves = (size/k) -1, remainder = size > k ? (size % k) : 0, i =0;
        ListNode current = head;
        while(current != null){
            result[i++] = current;
            ListNode lastNode = this.getLastNode(current, moves);
            if(remainder > 0){
                lastNode = lastNode == null ? lastNode : lastNode.next;
                remainder--;
            }
            head = lastNode == null ? lastNode : lastNode.next;
            if(lastNode != null)
                lastNode.next = null;
            current = head;
        }
        return result;
    }
    
    private int getListSize(ListNode head){
        ListNode temp = head;
        int size = 0;
        while(temp!=null){
            temp = temp.next;
            size++;
        }
        return size;
    }
    
    private ListNode getLastNode(ListNode start, int moves){
        ListNode temp = start;
        while(moves>0){
            temp = temp == null ? temp : temp.next;
            moves--;
        }
        return temp;
    }
}
public class SplitLinkedListInParts {

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
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        ListNode l8 = new ListNode(8);
        ListNode l9 = new ListNode(9);
        ListNode l10 = new ListNode(10);
    
        l1.next = l2;
        l2.next = l3;
//        l3.next = l4;
//        l4.next = l5;
//        l5.next = l6;
//        l6.next = l7;
//        l7.next = l8;
//        l8.next = l9;
//        l9.next = l10;
        
        l1.printList();
        ListNode res[] = solution.splitListToParts(l1,5);
        
        for(ListNode l : res)
            l.printList();
    }
    
}
