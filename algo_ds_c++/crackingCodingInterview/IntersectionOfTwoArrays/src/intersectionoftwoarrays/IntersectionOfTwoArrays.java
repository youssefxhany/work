/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intersectionoftwoarrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class Solution {

    public int[] intersection(int[] nums1, int[] nums2) {
        return nums1.length == 0 || nums2.length == 0 ? new int[0] : this.findCommon(nums1, nums2);
    }

    private int[] findCommon(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Boolean> map = this.generateMap(nums1.length > nums2.length ? nums1 : nums2);
        for (int num : (nums1.length > nums2.length ? nums2 : nums1)) {
            if (map.containsKey(num) && !map.get(num)) {
                result.add(num);
                map.put(num, Boolean.TRUE);
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    private Map<Integer, Boolean> generateMap(int[] nums) {
        Map<Integer, Boolean> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, Boolean.FALSE);
        }
        return map;
    }
}

public class IntersectionOfTwoArrays {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution2 = new Solution();
        int num1[] = {1,2,2,1};
        int num2[] = {2,2};
        printArray(solution2.intersection(num1,num2));
    }
    
    private static void printArray(int[] arr){
        for(int a: arr)
            System.out.print(a + ",");
    }

}
