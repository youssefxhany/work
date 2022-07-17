/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intersectionoftwolinkedlists;

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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int size1 = this.getListSize(headA), size2 = this.getListSize(headB);
        ListNode longest = size1 > size2 ? headA : headB;
        ListNode shortest = size1 > size2 ? headB : headA;
        longest = this.moveAhead(longest, Math.abs(size1 - size2));
        while (shortest != null && longest != null) {
            if (shortest == longest) {
                return longest;
            }
            shortest = shortest.next;
            longest = longest.next;
        }
        return null;
    }

    private int getListSize(ListNode node) {
        int size = 0;
        ListNode temp = node;
        while (node != null) {
            node = node.next;
            size++;
        }
        return size;
    }

    private ListNode moveAhead(ListNode node, int moves) {
        while (moves > 0) {
            node = node.next;
            moves--;
        }
        return node;
    }
}

public class IntersectionOfTwoLinkedLists {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

}
