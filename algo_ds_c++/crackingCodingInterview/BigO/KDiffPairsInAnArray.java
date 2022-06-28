/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package k.diff.pairs.in.an.array;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author youssef hany
 */

//nums[i] - nums[j] == k
//nums[i] - k = nums[j] 
class Solution {

    public int findPairs(int[] nums, int k) {
        return nums.length == 0 ? 0 : this.countPairs(nums, k);
    }

    private int countPairs(int[] nums, int k){
        int pairsCounter =0, duplicatesCounter = 0;
        Map<Integer,Boolean> numMap = this.buildMap(nums);
        Map<Integer,Integer> pairs = new HashMap();
        for(Integer num: numMap.keySet()){
            int value = num - k;
            if(numMap.containsKey(value) && !this.containsPair(pairs,num,value)){
                if(k == 0 && numMap.get(value)) duplicatesCounter++;
                if(value == num) continue;
                pairs.put(num,value);
                pairsCounter++;
            }
        }
        return pairsCounter + duplicatesCounter;
    }
    
    private Boolean containsPair(Map<Integer,Integer> pairs, int num1, int num2){
        return (pairs.containsKey(num1) && pairs.get(num1) == num2)  || (pairs.containsKey(num2) && pairs.get(num2) == num1 );
    }
    
    private Map<Integer,Boolean> buildMap(int[] nums){
        Map<Integer,Boolean> numMap = new HashMap<>();
        for(int num: nums){
            numMap.put(num,numMap.containsKey(num) ? true : false);
        }
        return numMap;
    }

}

public class KDiffPairsInAnArray {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int nums[] = {-1, 1};
        Solution solution = new Solution();
        System.out.println(solution.findPairs(nums, 0));
    }

}
