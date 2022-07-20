/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wigglesubsequence;

/**
 *
 * @author youssef hany
 */
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int sign = Integer.MAX_VALUE;
        int maxLength = nums.length;
        for(int i=0; i<=nums.length-2; i++){
            int diff = nums[i+1] - nums[i];
            int tempsign = (diff < 0) ? -1 : (diff > 0) ? 1 : Integer.MAX_VALUE; 
            if(sign == tempsign || diff == 0){
                maxLength--;
                continue;
            }
            sign = tempsign;
        }
        return maxLength;
    }
}
public class WiggleSubsequence {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
