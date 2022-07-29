/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergenodesinbetweenzeros;

/**
 *
 * @author youssef hany
 */
class ListNode{
    int val;
    ListNode next;

    public ListNode() {
    }
    
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    
    public void printNodes(){
        ListNode temp = this;
        while(temp!=null){
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println("");
    }
}

class Solution {
    public ListNode mergeNodes(ListNode head) {
        ListNode start = head, end = head;
        int sum = 0;
        while(end != null){
            if(end.next.val != 0){
                sum += end.next.val;
                end = end.next;
            }else{
                start.val = sum;
                sum = 0;
                if(end.next.next == null){
                    start.next = null;
                    break;
                }
                start.next = end.next;
                start = end = end.next;
            }
        }
        return head;
    }
}

public class MergeNodesInBetweenZeros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
