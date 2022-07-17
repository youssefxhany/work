/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlistcycleii;

import java.util.HashSet;
import java.util.Set;

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

    public ListNode detectCycle(ListNode head) {
        Set<ListNode> nodes = new HashSet<>();
        ListNode current = head;
        while (current != null) {
            if (nodes.contains(current)) {
                return current;
            }
            nodes.add(current);
            current = current.next;
        }
        return null;
    }
}

class OtherSolution {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode current = head;
        int steps = 1;
        while (current != null) {
            ListNode nodeKStepsAway = this.moveKStepsAway(current, steps);
            if (nodeKStepsAway == current) {
                break;
            }
            if (nodeKStepsAway == null) {
                return null;
            }
            current = current.next;
            steps++;
        }
        current = head;
        while (current != this.moveKStepsAway(current, steps)) {
            current = current.next;
        }
        return current;
    }

    private ListNode moveKStepsAway(ListNode current, int steps) {
        ListNode temp = current;
        while (steps > 0) {
            temp = temp.next;
            if (temp == null) {
                return null;
            }
            steps--;
        }
        return temp;
    }
}

class OtherSolution2 {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        
        ListNode slowRunner = head;
        ListNode fastRunner = head;
        while(fastRunner != null && fastRunner.next != null){
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
            if(fastRunner == slowRunner)
                break;
        }
        
        if(fastRunner == null || fastRunner.next == null)
            return null;
        
        fastRunner = head;
        while(fastRunner != slowRunner){
            fastRunner = fastRunner.next;
            slowRunner = slowRunner.next;
        }
        return slowRunner;
    }
}

public class LinkedListCycleII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OtherSolution solution = new OtherSolution();
        ListNode l1 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l13 = new ListNode(3);
        ListNode l14 = new ListNode(4);
        ListNode l15 = new ListNode(5);
        ListNode l16 = new ListNode(6);
        ListNode l17 = new ListNode(7);
        ListNode l18 = new ListNode(8);

        l1.next = l12;
        l12.next = l13;
        l13.next = l14;
        l14.next = l15;
        l15.next = l16;
        l16.next = l17;
        l17.next = l18;
        l18.next = l12;

        //l1.PrintList();
        ListNode node = solution.detectCycle(l1);
        if (node == null) {
            System.out.println("NO CYCLE");
        } else {
            System.out.println(node.val);
        }
    }

}
