/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rearrangearrayelementsbysign;

/**
 *
 * @author youssef hany
 */
class Solution {
    public int[] rearrangeArray(int[] nums) {
        if(nums.length <= 1) return nums;
        int rearranged[] = new int[nums.length];
        int positive = 0, negative = 1;
        for(int i =0; i<nums.length; i++){
            if(nums[i] < 0){
                rearranged[negative] = nums[i];
                negative += 2;
            }else{
                rearranged[positive] = nums[i];
                positive += 2;
            }
        }
        return rearranged;
    }
}
public class RearrangeArrayElementsBySign {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("hereee");
        int nums[] = {3,1,-2,-5,2,-4};
        
        for(int i=0; i<nums.length; i++){
            System.out.print(nums[i] + ">>");
        }
        System.out.println("");
        
        nums = solution.rearrangeArray(nums);
        
        for(int i=0; i<nums.length; i++){
            System.out.print(nums[i] + ">>");
        }
        System.out.println("");
    }
    
}
