/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intersectionoftwoarrays2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class Solution {

    public int[] intersect(int[] nums1, int[] nums2) {
        return nums1.length == 0 || nums2.length == 0 ? new int[0] : this.findCommon(nums1, nums2);
    }

    private int[] findCommon(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = this.generateMap(nums1.length > nums2.length ? nums1 : nums2);
        for (int num : (nums1.length > nums2.length ? nums2 : nums1)) {
            if (map.containsKey(num) && map.get(num) > 0) {
                    result.add(num);
                    map.put(num, map.get(num) -1);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    private Map<Integer, Integer> generateMap(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.containsKey(num) ? map.get(num) + 1 : 1);
        }
        return map;
    }
}

public class IntersectionOfTwoArrays2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution2 = new Solution();
        int num1[] = {1,2,2,1};
        int num2[] = {2,2};
        printArray(solution2.intersect(num1,num2));
    }
    
    private static void printArray(int[] arr){
        for(int a: arr)
            System.out.print(a + ",");
    }
    
}
