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

    private int countPairs(int[] nums, int k) {
        int pairsCounter = 0, duplicatesCounter = 0;
        Map<Integer, Boolean> numMap = this.generateMap(nums);
        Map<Integer, Integer> pairs = new HashMap();
        for (Integer num : numMap.keySet()) {
            int value = num - k;
            if (numMap.containsKey(value)) {
                if (numMap.get(num) && k == 0) {
                    duplicatesCounter++;
                }
                if (value == num || (this.pairExists(pairs, num, value) || this.pairExists(pairs, value, num))) {
                    continue;
                }
                pairs.put(num, value);
                pairsCounter++;
            }
        }
        return pairsCounter + duplicatesCounter;
    }

    private Map<Integer, Boolean> generateMap(int[] nums) {
        Map<Integer, Boolean> numMap = new HashMap<>();
        for (int number : nums) {
            numMap.put(number, numMap.containsKey(number));
        }
        return numMap;
    }

    private Boolean pairExists(Map<Integer, Integer> map, int pair1, int pair2) {
        return map.containsKey(pair1) && map.get(pair1) == pair2;
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
