/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intersectionoftwosortedarrays;

/**
 *
 * @author youssef hany
 */
//Question: Given two sorted arrays, find the number of elements in common. The arrays are the same length 
//and each has all distinct elements. 
class Solution{
    
    public int solve(int[] arr1, int[] arr2){
        return arr1.length == 0 ? 0 : this.getNumCommonElements(arr1, arr2);
    }
    
    private int getNumCommonElements(int[] arr1, int[] arr2){
        int arr1Index = 0;
        int arr2Index = 0;
        int counter = 0;
        int length = arr1.length;
        while(arr1Index < length && arr2Index < length){
            if(arr1[arr1Index] == arr2[arr2Index]){
                arr1Index++;
                arr2Index++;
                counter++;
            }else if(arr1[arr1Index] > arr2[arr2Index]){
                arr2Index++;
            }else{
                arr1Index++;
            }
        }
        return counter;
    }
}

public class IntersectionOfTwoSortedArrays {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int arr1[] = {13,27,35,40,49,55,59};
        int arr2[] = {17,35,39,40,55,58,60};
        Solution solution = new Solution();
        System.out.println(solution.solve(arr1, arr2));
    }
    
}
