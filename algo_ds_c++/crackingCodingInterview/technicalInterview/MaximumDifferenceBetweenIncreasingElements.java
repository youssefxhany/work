/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maximumdifferencebetweenincreasingelements;

/**
 *
 * @author youssef hany
 */
//Given a 0-indexed integer array nums of size n, find the maximum difference between nums[i] and nums[j] (i.e., nums[j] - nums[i]), such that 0 <= i < j < n and nums[i] < nums[j].
//
//Return the maximum difference. If no such i and j exists, return -1.
class Solution {

    public int maximumDifference(int[] nums) {
        int min = nums[0] < nums[1] ? nums[0] : nums[1];
        int maxDiff = nums[1] > nums[0] && nums[1] - nums[0] > -1 ? nums[1] - nums[0] : -1;
        for (int i = 2; i < nums.length; i++) {
            maxDiff = nums[i] - min > maxDiff && nums[i] - min > 0  ? nums[i] - min : maxDiff;
            min = min > nums[i] ? nums[i] : min;
        }
        return maxDiff;
    }
}

public class MaximumDifferenceBetweenIncreasingElements {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[]= {999,997,980,976,948,940,938,928,924,917,907,907,881,878,864,862,859,857,848,840,824,824,824,805,802,798,788,777,775,766,755,748,735,732,727,705,700,697,693,679,676,644,634,624,599,596,588,583,562,558,553,539,537,536,509,491,485,483,454,449,438,425,403,368,345,327,287,285,270,263,255,248,235,234,224,221,201,189,187,183,179,168,155,153,150,144,107,102,102,87,80,57,55,49,48,45,26,26,23,15};
        System.out.println(solution.maximumDifference(nums));
    }

}
