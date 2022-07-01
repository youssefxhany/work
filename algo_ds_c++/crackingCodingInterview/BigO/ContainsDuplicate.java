/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containsduplicate;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author youssef hany
 */
class Solution {
    public boolean containsDuplicate(int[] nums) {
        return nums.length == 0 ? false : this.hasDuplicates(nums);
    }
    
    private boolean hasDuplicates(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int num: nums){
            if(set.contains(num))
                return true;
            set.add(num);
        }
        return false;
    }
}

public class ContainsDuplicate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {1,1,1,3,3,4,3,2,4,2};
        System.out.println(solution.containsDuplicate(nums));
    }
    
}
