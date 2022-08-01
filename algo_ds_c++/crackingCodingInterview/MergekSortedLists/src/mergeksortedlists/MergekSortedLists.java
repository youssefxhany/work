/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergeksortedlists;

import java.util.PriorityQueue;

/**
 *
 * @author youssef hany
 */
//Definition for singly-linked list.
class ListNode{

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

class ListNodeWrapper implements Comparable<ListNodeWrapper>{

    ListNode listNode;

    ListNodeWrapper(ListNode node) {
        this.listNode = node;
    }

    @Override
    public int compareTo(ListNodeWrapper other) {
        if(this.listNode.val == other.listNode.val) return 0;
        if(this.listNode.val < other.listNode.val) return -1;
        return 1; 
    }
    
}

class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        ListNode head = null, current = null;
        PriorityQueue<ListNodeWrapper> heap = new PriorityQueue<>();
        for(int i=0; i<lists.length; i++)
            if(lists[i] != null)
                heap.add(new ListNodeWrapper(lists[i]));
        while(!heap.isEmpty()){
            ListNode min = heap.poll().listNode;
            if(min.next != null) heap.add(new ListNodeWrapper(min.next));
            if(head == null){
                head = current = min;
                continue;
            }
            current.next = min;
            current = min;
        }
        return head;
    }
}

public class MergekSortedLists {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(5);
        
        l11.next = l12;
        l12.next = l13;
        
        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(4);
        
        l21.next = l22;
        l22.next = l23;
        
        ListNode l31 = new ListNode(2);
        ListNode l32 = new ListNode(6);
        
        l31.next = l32;
        
        ListNode lists[] = {l11,l21,l31};
        
        ListNode head = solution.mergeKLists(lists);
        
        head.printList();
    }

}
