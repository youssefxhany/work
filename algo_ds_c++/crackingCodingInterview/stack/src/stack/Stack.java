/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack;

/**
 *
 * @author youssef hany
 */
public class Stack {

    /**
     * @param args the command line arguments
     */
    private Integer[] stack;
    private Integer top;
    private Integer size;
    private final String isFullErrorMessage = "CAN'T INSERT, STACK IS FULL";
    private final String isEmptyErrorMessage = "CAN'T POP, STACK IS EMPTY";
    
    public Stack(Integer size){
        this.stack = new Integer[size];
        this.size = size;
        this.top = -1;
    }
    
    public void push(Integer element) throws Exception{
        if(this.isFull()){
            throw new Exception(this.isFullErrorMessage);
        }
        this.stack[++this.top] = element;
        return;
    }
    
    public Integer pop() throws Exception{
        if(this.isEmpty()){
            throw new Exception(this.isEmptyErrorMessage);
        }
        return this.stack[this.top--];
    }
    
    public Integer peek() throws Exception{
        if(this.isEmpty()){
            throw new Exception(this.isEmptyErrorMessage);
        }
        Integer element = this.pop();
        this.push(element);
        return element;
    }
    
    public boolean isFull(){
        return this.top == (this.size -1) ? true : false;
    }
    
    public boolean isEmpty(){
        return this.top == -1 ? true : false;
    }
    
    public void printStack(){
        for(int i=0; i<=top; i++){
            System.out.println(this.stack[i]);
        }
    }
    
    public static void main(String[] args) throws Exception {
        Stack stack = new Stack(6);
    }
    
}
