/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containsduplicateii;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        return nums.length == 0 || k == 0 ? false : this.hasDuplicate(nums,k);
    }
    
    private boolean hasDuplicate(int[] nums, int k){
        Map<Integer,Integer> window = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(window.size() == (k+1)) window.remove(nums[i-k-1]);
            if(!window.containsKey(nums[i]))
                window.put(nums[i], i);
            else
                return true;
        }
        return false;
    }
}
public class ContainsDuplicateII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {1,2,3,1};
        System.out.println(solution.containsNearbyDuplicate(nums, 3));
    }
    
}
