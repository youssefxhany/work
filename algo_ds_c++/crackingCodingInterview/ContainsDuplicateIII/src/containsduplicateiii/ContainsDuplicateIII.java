/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containsduplicateiii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author youssef hany
 */
class SolutionONK {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        return nums.length == 0 ? false : this.hasDuplicates(nums, k, t);
    }

    private boolean hasDuplicates(int[] nums, int k, int t) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= (i + k) && j < nums.length; j++) {
                if (Math.abs(new Long(nums[i]) - new Long(nums[j])) <= t) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void printArray(int arr[]){
        for(int a: arr)
            System.out.print(a + "-->");
        System.out.println("");
    }
}

class Solution {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        return nums.length == 0 || k==0 ? false : this.hasDuplicates(nums, k, t);
    }
    
    private boolean hasDuplicates(int[] nums, int k, int t){
        TreeSet<Long> tree = new TreeSet<>();
        for(int i=0; i<nums.length; i++){
            if(tree.size() == k+1)
                tree.remove(new Long(nums[i-k-1]));
            Long ceilling = tree.ceiling(new Long(nums[i]-t));
            Long floor = tree.floor(new Long(nums[i]+t));
            if(ceilling != null && ceilling <= (new Long(nums[i])+t))
                return true;
            if(floor != null && floor >= (new Long(nums[i])-t))
                return true;
            tree.add(new Long(nums[i]));
        }
        return false;
    }
    
}

public class ContainsDuplicateIII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {2147483640,2147483641};
        System.out.println(solution.containsNearbyAlmostDuplicate(nums, 1, 100));
    }

}
