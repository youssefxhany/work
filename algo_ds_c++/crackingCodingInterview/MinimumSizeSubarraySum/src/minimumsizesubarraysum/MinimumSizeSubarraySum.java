/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimumsizesubarraysum;

import java.util.Arrays;

/**
 *
 * @author youssef hany
 */
class BruteForceSolution {
    public int minSubArrayLen(int target, int[] nums) {
        int start , end, windowSum , minWindowSize = Integer.MAX_VALUE;
        for(int i=0; i<nums.length; i++){
            start = end = i;
            windowSum = nums[start];
            while(end < nums.length && windowSum < target){
                if(nums[end] == target)
                    return 1;
                end++;
                if(end >= nums.length) break;
                windowSum += nums[end];
            }
            if(windowSum >= target){
                minWindowSize = minWindowSize <= (end - start + 1) && minWindowSize != 0 ? minWindowSize : (end - start + 1);
                continue;
            }
            if(end >= nums.length && windowSum == Integer.MAX_VALUE){
                return 0;
            }
        }
        if(minWindowSize == Integer.MAX_VALUE) return 0;
        return minWindowSize;
    }
}
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int minWindowSize = Integer.MAX_VALUE, start = 0 , end = 0;
        int sum = nums[end];
        while(end < nums.length){
            if(nums[end] >= target) return 1;
            if(sum < target){
                end++;
                sum += (end < nums.length) ? nums[end] : 0;
                continue;
            }
            if(sum == target){
                minWindowSize = minWindowSize <= (end - start + 1) ? minWindowSize : (end - start + 1);
                start = end;
                sum = nums[end];
                continue;
            }
            if(sum > target){
                if(sum >= target)
                    minWindowSize = minWindowSize <= (end - start + 1) ? minWindowSize : (end - start + 1);
                sum -= nums[start];
                start++;
                continue;
            }
        }
        if(sum >= target)
            minWindowSize = minWindowSize <= (end - start + 1) ? minWindowSize : (end - start + 1);
        if(minWindowSize == Integer.MAX_VALUE) return 0;
        return minWindowSize;
    }
}
public class MinimumSizeSubarraySum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int target = 15;
        int nums[]= {2,14};
        System.out.println(solution.minSubArrayLen(target, nums));
    }
    
}
