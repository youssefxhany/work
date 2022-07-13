/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longestrepeatingcharacterreplacement;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
//You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
//
//Return the length of the longest substring containing the same letter you can get after performing the above operations.
class Solution {

    public int characterReplacement(String s, int k) {
        return s.length() == 0 ? 0 : this.getLongestSubstring(s, k);
    }

    private int getLongestSubstring(String s, int k) {
        int maxLength = 0;
        int windowStart = 0, windowEnd = 0;
        int frequencyMap[] = new int[26];
        int maxCount = 0;
        while (windowEnd < s.length()) {
            frequencyMap[((int)s.charAt(windowEnd))-65]++;
            maxCount = Math.max(maxCount, frequencyMap[((int)s.charAt(windowEnd))-65]);
            while(this.cannotMaintainWindow(k, (windowEnd - windowStart + 1), maxCount)) {
                frequencyMap[((int)s.charAt(windowStart))-65]--;
                windowStart++;
            }
            maxLength = maxLength < (windowEnd - windowStart + 1) ? (windowEnd - windowStart + 1) : maxLength;
            windowEnd++;
        }
        return maxLength < (windowEnd - windowStart) && !this.cannotMaintainWindow(k, (windowEnd - windowStart), maxCount) ? (windowEnd - windowStart) : maxLength;
    }

    private boolean cannotMaintainWindow(int k, int windowSize, int maxCount) {
        return windowSize - maxCount > k;
    }
}

public class LongestRepeatingCharacterReplacement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "AAAA";
        System.out.println(solution.characterReplacement(s, 2));
    }

}
