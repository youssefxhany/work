/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twosum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,List<Integer>> map = this.buildMap(nums);
        int result[] = new int[2];
        for(int key: map.keySet()){
            int neededValue = target - key;
            if(map.containsKey(neededValue) && (neededValue != key || map.get(neededValue).size() > 1)){
                result[0] = map.get(key).get(0);
                result[1] = map.get(neededValue).get(neededValue == key ? 1 : 0);
                return result;
            }
        }
        return result;
    }
    
    private Map<Integer,List<Integer>> buildMap(int[] nums){
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            List<Integer> indexList = map.containsKey(nums[i]) ? map.get(nums[i]) : new ArrayList<>();
            if(indexList.size() < 2)
                indexList.add(i);
            map.put(nums[i], indexList);
        }
        return map;
    }
}

public class TwoSum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int nums[] = {3,3};
        int res[] = solution.twoSum(nums, 6);
        printResult(res);
    }
    
    private static void printResult(int[] res){
        System.out.println("(" + res[0] + "," + res[1] + ")");
    }
}
