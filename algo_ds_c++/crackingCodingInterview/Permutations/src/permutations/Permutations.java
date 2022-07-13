/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author youssef hany
 */
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        if(nums.length == 1)
            return Arrays.asList(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        return this.insertAtEveryPos(permute(Arrays.copyOfRange(nums, 0, nums.length-1)),nums[nums.length-1]);
    }
    
    private List<List<Integer>> insertAtEveryPos(List<List<Integer>> arrs, int lastInteger){
        List<List<Integer>> res = new ArrayList<>();
        for(List<Integer> arr : arrs){
            for(int i=0; i<=arr.size(); i++){
                List<Integer> permutation = new ArrayList<>(arr);
                permutation.add(i,new Integer(lastInteger));
                res.add(permutation);
            }
        }
        return res;
    }
    
}
public class Permutations {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {1,2,3};
        printArrays(solution.permute(nums));
    }
    
    private static void printArrays(List<List<Integer>> nums){
        for(List<Integer> num: nums){
            for(int i=0; i<num.size();i++)
                System.out.print(num.get(i)+"-->");
            System.out.println("");
        }
    }
    
}
