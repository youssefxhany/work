/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partitionarrayaccordingtogivenpivot;

import java.util.ArrayList;

/**
 *
 * @author youssef hany
 */
class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        if(nums.length == 0) return nums;
        int lowerThanPicotCounter = 0, equalToPivotCounter = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < pivot) lowerThanPicotCounter++;
            if(nums[i] == pivot) equalToPivotCounter++;
        }
        int p1= 0, p2 =lowerThanPicotCounter, p3 = p2 + equalToPivotCounter;
        int modifiedNums[] = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            if(nums[i] < pivot){
                modifiedNums[p1] = nums[i];
                p1++;
                continue;
            }
            if(nums[i] == pivot) {
                modifiedNums[p2] = nums[i];
                p2++;
                continue;
            }
            if(nums[i] > pivot) {
                modifiedNums[p3] = nums[i];
                p3++;
                continue;
            }
        }
        return modifiedNums;
    }
}
public class PartitionArrayAccordingToGivenPivot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        int nums[] = {9,12,5,10,14,3,10};
        int pivot = 10;
        
        nums = solution.pivotArray(nums, pivot);
        
        for(int i=0; i<nums.length; i++){
            System.out.print(nums[i] + "-->");
        }
        System.out.println("");
    }
    
}
