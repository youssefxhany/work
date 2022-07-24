package removelinkedlistelements;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    public void printList(){
        ListNode temp = this;
        while (temp != null){
            System.out.print(temp.val + "-->");
            temp = temp.next;
        }
        System.out.println();
    }
}

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null || (head.next == null && head.val == val)) return null;
        ListNode before = null;
        ListNode current = head;
        while(current != null){
            if(current.val == val){
                if(before == null){
                    head = current.next;
                    current = head;
                }else {
                    before.next = current.next;
                    current = current.next;
                }
                continue;
            }
            before = current;
            current = current.next;
        }
        return head;
    }
}
public class RemoveLinkedListElements {

    public static void main(String[] args) {
	// write your code here
    }
}