/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minstack;

/**
 *
 * @author youssef hany
 */
class ListNode{
    int data;
    ListNode next;
    ListNode nextMin;

    public ListNode(int data) {
        this.data = data;
        this.next = null;
        this.nextMin = null;
    }
}

class MinStack {

    private ListNode top;
    private ListNode min;
            
    public MinStack() {
        this.top = this.min = null;
    }
    
    public void push(int val) {
        ListNode node = new ListNode(val);
        if(top == null){
            top = min = node;
            return;
        }
        node.next = top;
        top = node;
        if(node.data <= min.data){
            node.nextMin = min;
            min = node;
        }
    }
    
    public void pop() {
        if(top == null) return;
        if(top == min){
            min = min == null ? null : min.nextMin;
        }
        top = top.next;
        return ;
    }
    
    public int top() {
        if(top == null) return -1;
        return top.data;
    }
    
    public int getMin() {
        if(min == null) return -1;
        return min.data;
    }
}
