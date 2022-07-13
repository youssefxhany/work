/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package removeduplicatesfromanunsortedlinkedlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

//we remove dups except 1st instance
class Solution {

    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pointer1 = head, pointer2 = head.next;
        Set<Integer> elements = new HashSet<>();
        elements.add(pointer1.val);
        while (pointer2 != null) {
            if (!elements.contains(pointer2.val)) {
                elements.add(pointer2.val);
            } else {
                pointer1.next = pointer2.next;
                pointer2 = pointer2.next;
                continue;
            }
            pointer2 = pointer2.next;
            pointer1 = pointer1.next;
        }
        return head;
    }
}

//we remove all dups even qst instance
class Solution2 {

    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Map<Integer, Boolean> dupsMap = this.buildDupsMap(head);
        ListNode pointer1 = head, pointer2 = head.next;
        while (pointer2 != null) {
            if (dupsMap.get(pointer1.val) && head == pointer1) {
                pointer1 = pointer1.next;
                head = pointer1;
                pointer2 = pointer2.next;
                continue;
            }
            if (dupsMap.get(pointer2.val)) {
                pointer1.next = pointer2.next;
                pointer2 = pointer2.next;
                continue;
            }
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        if (head == pointer1 && pointer1.next == null) {
            head = null;
        }
        return head;
    }

    private Map<Integer, Boolean> buildDupsMap(ListNode head) {
        Map<Integer, Boolean> dupsMap = new HashMap<>();
        ListNode node = head;
        while (node != null) {
            if (!dupsMap.containsKey(node.val)) {
                dupsMap.put(node.val, Boolean.FALSE);
            } else {
                dupsMap.put(node.val, Boolean.TRUE);
            }
            node = node.next;
        }
        return dupsMap;
    }
}

//same as solution1 but without extra buffer
class Solution3 {

    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head;
        while(node != null){
            ListNode runner = node;
            while(runner != null && runner.next != null){
                if(runner.next.val == node.val){
                    runner.next = runner.next.next;
                    continue;
                }
                runner = runner.next;
            }
            node = node.next;
        }
        return head;
    }

}

public class RemoveDuplicatesFromAnUnsortedLinkedList {

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
//        head.printNodes();
//        Solution solution = new Solution();
//        head = solution.deleteDuplicatesUnsorted(head);
//        head.printNodes();
//        System.out.println("-------------------------");


//        head.printNodes();
//        Solution2 solution2 = new Solution2();
//        head = solution2.deleteDuplicatesUnsorted(head);
//        if (head == null) {
//            System.out.println("empty linked list");
//        } else {
//            head.printNodes();
//        }
//        System.out.println("-------------------------");


        head.printNodes();
        Solution3 solution3 = new Solution3();
        head = solution3.deleteDuplicatesUnsorted(head);
        if (head == null) {
            System.out.println("empty linked list");
        } else {
            head.printNodes();
        }
        System.out.println("-------------------------");
    }

}
