/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deletemiddlenode;

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

    public void deleteMiddleNode(ListNode node) {
        if (node.next == null) {
            return;
        }
        ListNode current = node, next = current.next;
        while (current != null && next != null) {
            this.swap(current, next);
            if (next.next == null) {
                current.next = null;
            }
            current = next;
            next = next.next;
        }
        return;
    }

    private void swap(ListNode node1, ListNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
        return;
    }
}

class BetterSolution {

    public void deleteMiddleNode(ListNode node) {
        if (node == null || node.next == null) {
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
        return;
    }
}

public class DeleteMiddleNode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        BetterSolution betterSolution = new BetterSolution();

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

//        head.printNodes();
//        solution.deleteMiddleNode(n8);
//        head.printNodes();

        head.printNodes();
        betterSolution.deleteMiddleNode(n8);
        head.printNodes();

    }

}
