/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findresultantarrayafterremovinganagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author youssef hany
 */
class Solution {

    public List<String> removeAnagrams(String[] words) {
        if (words.length <= 1) {
            return Arrays.asList(words);
        }
        int windowStart = 0, windowEnd = 1;
        List<String> wordsList = new ArrayList(Arrays.asList(words));
        while (windowEnd < wordsList.size()) {
            if (this.isAnagram(wordsList.get(windowStart), wordsList.get(windowEnd))) {
                wordsList.remove(windowEnd);
            } else {
                windowEnd++;
                windowStart++;
            }
        }
        return wordsList;
    }

    private boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int frequencyMap[] = this.buildFrequencyMap(s);
        int count = frequencyMap[26];
        for (Character c : t.toCharArray()) {
            if (frequencyMap[(int) c - 97] > 0) {
                frequencyMap[(int) c - 97]--;
                if (frequencyMap[(int) c - 97] == 0) {
                    count--;
                }
            } else {
                return false;
            }
        }
        return count == 0;
    }

    private int[] buildFrequencyMap(String str) {
        int frequencyMap[] = new int[27];
        int count = 0;
        for (Character c : str.toCharArray()) {
            if (frequencyMap[(int) c - 97] == 0) {
                count++;
            }
            frequencyMap[(int) c - 97]++;
        }
        frequencyMap[26] = count;
        return frequencyMap;
    }
    
    private void printFrequencyMap(int[] map){
        for(int i=0; i<map.length-1; i++)
            if(map[i] > 0)
                System.out.println((char)(i+97) + "  :  " + map[i]);
    }
}

public class FindResultantArrayAfterRemovingAnagrams {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String str[] = {"abba","baba","bbaa","cd","cd"};
        String str2[] = {"a","b","c","d","e"};
        
        Solution solution = new Solution();
        System.out.println(solution.removeAnagrams(str));
        System.out.println(solution.removeAnagrams(str2));
    }

}
