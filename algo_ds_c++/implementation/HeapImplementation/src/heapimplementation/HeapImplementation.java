/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heapimplementation;

import java.util.Arrays;

/**
 *
 * @author youssef hany
 */
class Heap{
    private int capacity = 10;
    private int size = 0;
    private int heap[] = new int[this.capacity];
    
    private int getLeftChildIndex(int parentIndex){ return 2 * parentIndex + 1; }
    private int getRightChildIndex(int parentIndex){ return 2 * parentIndex + 2; }
    private int getParentIndex(int childIndex){ return (childIndex-1)/2; }
    
    private boolean hasLeftChild(int parentIndex){return this.getLeftChildIndex(parentIndex) < this.size;}
    private boolean hasRightChild(int parentIndex) {return this.getRightChildIndex(parentIndex) < this.size;}
    private boolean hasParent(int childIndex) {return this.getParentIndex(childIndex) >= 0;}
    
    private int getLeftChild(int parentIndex){
        if(!this.hasLeftChild(parentIndex)) throw new IllegalStateException("has no left child");
        return this.heap[this.getLeftChildIndex(parentIndex)];
    }
    
    private int getRightChild(int parentIndex){
        if(!this.hasRightChild(parentIndex)) throw new IllegalStateException("has no right child");
        return this.heap[this.getRightChildIndex(parentIndex)];
    }
    
    private int getParent(int childIndex){
        if(!this.hasParent(childIndex)) throw new IllegalStateException("has no parent");
        return this.heap[this.getParentIndex(childIndex)];
    }
    
    private void swap(int index1, int index2){
        int temp = this.heap[index1];
        this.heap[index1] = this.heap[index2];
        this.heap[index2] = temp;
    }
    
    private void extendHeap(){
        if(this.size == this.capacity){
            this.heap = Arrays.copyOf(this.heap, this.capacity * 2);
            this.capacity *= 2;
        }
    }
    
    public int peek(){
        return this.heap[0];
    }
    
    public void add(int value){
        this.extendHeap();
        this.heap[this.size] = value;
        this.size++;
        this.heapifyUp();
    }
    
    public int poll(){
        int data = this.heap[0];
        this.heap[0] = this.heap[this.size-1];
        this.size--;
        this.visualizeHeap();
        this.heapifyDown();
        return data;
    }
    
    private void heapifyUp(){
        int startNodeIndex = this.size-1;
        while(this.hasParent(startNodeIndex) && this.getParent(startNodeIndex) > this.heap[startNodeIndex]){
            this.swap(startNodeIndex, this.getParentIndex(startNodeIndex));
            startNodeIndex = this.getParentIndex(startNodeIndex);
        }
    }
    
    private void heapifyDown(){
        int startNodeIndex = 0;
        while(this.hasLeftChild(startNodeIndex)){
            int smallestValue = Math.min(this.getLeftChild(startNodeIndex),
                    this.hasRightChild(startNodeIndex) ? this.getRightChild(startNodeIndex) : Integer.MAX_VALUE);
            int smallestIndex = smallestValue == this.getLeftChild(startNodeIndex) ?
                    this.getLeftChildIndex(startNodeIndex) : this.getRightChildIndex(startNodeIndex);
            if(this.heap[startNodeIndex] < smallestValue){
                break;
            }
            this.swap(startNodeIndex, smallestIndex);
            startNodeIndex = smallestIndex;
        }
    }
    
    public void visualizeHeap() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.heap[i] + "-->");
        }
        System.out.println("");
    }
}
public class HeapImplementation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Heap heap = new Heap();
        
        heap.add(2);
        heap.add(3);
        heap.add(8);
        heap.add(4);
        heap.add(7);
        heap.add(10);
        heap.add(9);
        heap.add(15);
        heap.add(9);
        heap.add(13);
        heap.add(9);
        heap.add(14);
        heap.add(20);
        heap.add(11);
        heap.add(19);
        heap.add(20);
        heap.add(17);
        heap.add(20);
        
        heap.visualizeHeap();
        System.out.println("--------------------");
        
//        heap.add(3);
//        heap.visualizeHeap();
//        System.out.println("---------------------");
        
        System.out.println(heap.poll());
        heap.visualizeHeap();
        System.out.println("---------------------");
    }
    
}
