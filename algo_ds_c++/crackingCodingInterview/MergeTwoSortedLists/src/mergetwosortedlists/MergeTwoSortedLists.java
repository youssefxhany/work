/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergetwosortedlists;

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

    public void PrintList() {
        ListNode temp = this;
        while (temp != null) {
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println();
    }
}

class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null && list2 == null) return null;
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode list3 = null;
        ListNode current = list3;
        ListNode p1 = list1 , p2 = list2;
        while(p1 != null && p2 != null){
            ListNode newNode = new ListNode(p1.val < p2.val ? p1.val : p2.val);
            int oldP1 = p1.val, oldP2 = p2.val;
            p1 = oldP1 < oldP2 ? p1.next : p1;
            p2 = oldP1 < oldP2 ? p2 : p2.next;
            if(list3 == null){
                current = list3 = newNode;
                continue;
            }
            current.next = newNode;
            current = current.next;
        }
        current.next = p1 != null ? p1 : p2 != null ? p2 : null;
        return list3;
    }
}

public class MergeTwoSortedLists {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l13 = new ListNode(4);
        ListNode l14 = new ListNode(4);

        ListNode l2 = new ListNode(1);
        ListNode l21 = new ListNode(3);
        ListNode l23 = new ListNode(4);

        l1.next = l12;
        l12.next = l13;
//        l13.next = l14;
        l1.PrintList();

        l2.next = l21;
        l21.next = l23;
        l2.PrintList();
        System.out.println("-------------------------");

        l1 = solution.mergeTwoLists(l1, l2);
        l1.PrintList();
    }

}
