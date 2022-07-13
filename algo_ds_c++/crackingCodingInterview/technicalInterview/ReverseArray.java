/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reversearray;

import java.util.Stack;

/**
 *
 * @author youssef hany
 */
public class ReverseArray {

    /**
     * @param args the command line arguments
     */
    
    final static int SIZE = 10;
    public static void main(String[] args) {
        int[] myArray = new int[SIZE];
        for(int i=0; i<myArray.length; i++)
            myArray[i] = i;
        
        reverseImplicitly(myArray);
        
        for(int element: myArray) System.out.print(element + "-->");
        
        System.out.println("");
        
        reverseWithStack(myArray);
        
        for(int element: myArray) System.out.print(element + "-->");
        
        System.out.println("");
    }
    
    private static void reverseImplicitly(int[] arr){
        for(int i=0;i<arr.length/2;i++){
            swap(arr,i,arr.length-i-1);
        }
    }
    
    private static void swap(int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    
    private static void reverseWithStack(int[] arr){
        Stack stack = new Stack<>();
        for(int element: arr) stack.add(element);
        for(int i=0; i<arr.length; i++) arr[i] = (int)stack.pop();
    }
    
}
