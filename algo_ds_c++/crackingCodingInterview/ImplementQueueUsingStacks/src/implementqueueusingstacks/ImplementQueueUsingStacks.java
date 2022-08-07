/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementqueueusingstacks;

import java.util.Stack;

/**
 *
 * @author youssef hany
 */
class MyQueue {
    private Stack<Integer> stackNewest,stackOldest;

    public MyQueue() {
        this.stackNewest = new Stack();
        this.stackOldest = new Stack();
    }
    
    public void push(int x) {
        this.stackNewest.push(x);
    }
    
    public int pop() {
        if(stackNewest.isEmpty() && this.stackOldest.isEmpty()) return -1;
        this.shiftStacks();
        return this.stackOldest.pop();
    }
    
    public int peek() {
        if(stackNewest.isEmpty() && this.stackOldest.isEmpty()) return -1;
        this.shiftStacks();
        return this.stackOldest.peek();
    }
    
    public boolean empty() {
        return this.stackNewest.isEmpty() && this.stackOldest.isEmpty();
    }
    
    private void shiftStacks(){
        if(stackOldest.isEmpty()){
            while(!stackNewest.isEmpty())
                stackOldest.push(stackNewest.pop());
        }
    }
}
public class ImplementQueueUsingStacks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
