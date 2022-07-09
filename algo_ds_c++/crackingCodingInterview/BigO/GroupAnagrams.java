/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupanagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        return strs.length == 1 ? Arrays.asList(Arrays.asList(strs)) : this.getGroupAnagrams(strs);
    }

    private List<List<String>> getGroupAnagrams(String[] strs) {

        List<List<String>> results = new ArrayList<>();
        Map<Integer, List<String>> lengthMap = this.buildLengthMap(strs);
        for (Integer key : lengthMap.keySet()) {
            if (lengthMap.get(key).size() == 1) {
                results.add(lengthMap.get(key));
            } else {
                while (!lengthMap.get(key).isEmpty()) {
                    List<String> subRes = new ArrayList<>();
                    String str1 = lengthMap.get(key).get(0);
                    subRes.add(str1);
                    lengthMap.get(key).remove(str1);
                    for (int i = 0; i < lengthMap.get(key).size(); i++) {
                        String str2 = lengthMap.get(key).get(i);
                        if (this.isAnagram(str1, str2)) {
                            subRes.add(str2);
                            lengthMap.get(key).remove(str2);
                            i--;
                        }
                    }
                    results.add(subRes);
                }
            }
        }
        return results;
    }

    private Map<Integer, List<String>> buildLengthMap(String[] strs) {
        Map<Integer, List<String>> lengthMap = new HashMap<>();
        for (String str : strs) {
            List<String> list = lengthMap.containsKey(str.length()) ? lengthMap.get(str.length()) : new ArrayList<>();
            list.add(str);
            lengthMap.put(str.length(), list);
        }
        return lengthMap;
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
        int count = 0;
        int[] frequencyMap = new int[27];
        for (Character c : str.toCharArray()) {
            if (frequencyMap[(int) c - 97] == 0) {
                count++;
            }
            frequencyMap[(int) c - 97]++;
        }
        frequencyMap[26] = count;
        return frequencyMap;
    }
}

class EnhancedSolution {
    public List<List<String>> groupAnagrams(String[] strs) {
        return strs.length == 1 ? Arrays.asList(Arrays.asList(strs)) : this.getGroupAnagrams(strs);
    }
    
    private List<List<String>> getGroupAnagrams(String[] strs){
        Map<Map<Character,Integer>,List<String>> resultMap = new HashMap<>();
        for(String str: strs){
            Map<Character,Integer> freqencyMap = this.buildFrequencyMap(str);
            List<String> strList = resultMap.containsKey(freqencyMap) ? resultMap.get(freqencyMap) : new ArrayList<>();
            strList.add(str);
            resultMap.put(freqencyMap, strList);
        }
        return new ArrayList(resultMap.values());
    }
    
    private Map<Character,Integer> buildFrequencyMap(String str){
        Map<Character,Integer> frequencyMap = new HashMap<>();
        for(Character c: str.toCharArray())
            frequencyMap.put(c, frequencyMap.containsKey(c) ? frequencyMap.get(c) + 1 : 1);
        return frequencyMap;
    }
}

class MoreEnhancedSolution{
    public List<List<String>> groupAnagrams(String[] strs) {
        return strs.length == 1 ? Arrays.asList(Arrays.asList(strs)) : this.getGroupAnagrams(strs);
    }
    
    private List<List<String>> getGroupAnagrams(String[] strs){
        Map<String,List<String>> resultMap = new HashMap<>();
        for(String str: strs){
            char sortedStr[] = str.toCharArray();
            Arrays.sort(sortedStr);
            String sorted = new String(sortedStr);
            List<String> strList = resultMap.containsKey(sorted) ? resultMap.get(sorted) : new ArrayList<>();
            strList.add(str);
            resultMap.put(sorted, strList);
        }
        return new ArrayList(resultMap.values());
    }
}

public class GroupAnagrams {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String strs[] = {"eat", "tea", "tan", "ate", "nat", "bat", "anagram", "nagaram"};
        String s[] = {"nozzle","punjabi","waterlogged","imprison","crux","numismatists","sultans","rambles","deprecating","aware","outfield","marlborough","guardrooms","roast","wattage","shortcuts","confidential","reprint","foxtrot","dispossession","floodgate","unfriendliest","semimonthlies","dwellers","walkways","wastrels","dippers","engrossing","undertakings","unforeseen","oscilloscopes","pioneers","geller","neglects","pluralistic","tempests","enhancing","rostand","ebonies","chipped","bodice","causation","sharron","waxiness","lute","snoozes","dickered","gratifies","neuron","condemned","javelin","undisturbed","lauded","vetting","burrows","prolific","scarves","sorrowed","understatements","affabler","ampuls","malices","extemporizing"};
        
        Solution solution = new Solution();
        System.out.println(solution.groupAnagrams(strs));

        EnhancedSolution enhancedSolution = new EnhancedSolution();
        System.out.println(enhancedSolution.groupAnagrams(strs));
        
        MoreEnhancedSolution moreEnhancedSolution = new MoreEnhancedSolution();
        System.out.println(moreEnhancedSolution.groupAnagrams(strs));
    }

}
