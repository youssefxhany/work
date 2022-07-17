/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addtwonumbersii;

/**
 *
 * @author youssef hany
 */


//Definition for singly-linked list.
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = this.reverseList(l1);
        l2 = this.reverseList(l2);
        ListNode result = new ListNode();
        ListNode currentNode = null;
        int extra = 0;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int digit = (num1 + num2 + extra) % 10;
            extra = (num1 + num2 + extra) / 10;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            if (currentNode == null) {
                result.val = digit;
                currentNode = result;
                continue;
            }
            currentNode = this.allocateNewNode(currentNode, digit);
        }
        if (extra != 0) {
            currentNode = this.allocateNewNode(currentNode, extra);
        }
        currentNode.next = null;
        return this.reverseList(result);
    }

    private ListNode allocateNewNode(ListNode currentNode, int value) {
        ListNode newNode = new ListNode(value);
        currentNode.next = newNode;
        return currentNode.next;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode prev = head;
        ListNode current = prev.next;
        ListNode next = current.next;

        prev.next = null;

        while (current != null) {
            current.next = prev;
            prev = current;
            current = next;
            next = next == null ? null : next.next;
        }

        head = prev;
        return head;
    }
}

class OtherSolution {

    static int currentExtra = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        ListNode currentNode = null;
        int size1 = this.getListSize(l1), size2 = this.getListSize(l2), extra = 0;
        l1 = size1 > size2 ? l1 : this.addPaddingToList(l1, size2 - size1);
        l2 = size1 < size2 ? l2 : this.addPaddingToList(l2, size1 - size2);
        int tallestListSize = Math.max(size1, size2);

        while (tallestListSize > 0) {
            int digit = (l1.val + l2.val + extra) % 10;
            extra = (l1.val + l2.val + extra) / 10;
            l1 = l1.next;
            l2 = l2.next;
            tallestListSize--;
            if (currentNode == null) {
                currentNode = l3;
                currentNode.val = digit;
                l3 = this.handleExtraSum(l3, currentNode, extra);
                extra = 0;
                continue;
            }
            ListNode newNode = new ListNode(digit);
            currentNode.next = newNode;
            currentNode = newNode;
            l3 = this.handleExtraSum(l3, currentNode, extra);
            extra = 0;
        }
        return l3;
    }

    private int getListSize(ListNode list) {
        ListNode temp = list;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    private ListNode addPaddingToList(ListNode listNode, int num) {
        if (num == 0) {
            return listNode;
        }
        while (num > 0) {
            ListNode newNode = new ListNode(0);
            newNode.next = listNode;
            listNode = newNode;
            num--;
        }
        return listNode;
    }

    private ListNode handleExtraSum(ListNode head, ListNode current, int extra) {
        if (extra == 0) {
            return head;
        }
        currentExtra = extra;
        this.addExtra(head, current);
        if (currentExtra != 0) {
            ListNode newNode = new ListNode(extra);
            newNode.next = head;
            head = newNode;
        }
        currentExtra = 0;
        return head;
    }

    private void addExtra(ListNode head, ListNode current) {
        if (head == null || current == null || head == current || currentExtra == 0) {
            return;
        }
        addExtra(head.next, current);
        if (currentExtra == 0) {
            return;
        }
        int oldVal = head.val;
        head.val = (oldVal + currentExtra) % 10;
        currentExtra = (oldVal + currentExtra) / 10;
        return;
    }
}

public class AddTwoNumbersII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OtherSolution otherSolution = new OtherSolution();

        ListNode l1 = new ListNode(9);
        ListNode l12 = new ListNode(9);
        ListNode l13 = new ListNode(9);
        ListNode l14 = new ListNode(9);

        ListNode l2 = new ListNode(9);
        ListNode l21 = new ListNode(9);
        ListNode l23 = new ListNode(9);

        l1.next = l12;
        l12.next = l13;
        l13.next = l14;

        l1.PrintList();

        l2.next = l21;
        l21.next = l23;

        l2.PrintList();
        System.out.println("-------------------------");

        otherSolution.addTwoNumbers(l1, l2);
    }

}
