/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longestsubstringwithoutrepeatingcharacters;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        return s.length() == 0 ? 0 : s.length() == 1 ? 1 : this.findLongestSubString(s);
    }
    
    private int findLongestSubString(String s){
        Map<Character,Integer> map = new HashMap<>();
        int maxLength = 0;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                maxLength = map.size() > maxLength ? map.size() : maxLength;
                map = this.clearCharacters(map, map.get(c));
                map.put(c, i);
            }else{
                map.put(c, i);
            }
        }
        if(!map.isEmpty())
            maxLength = map.size() > maxLength ? map.size() : maxLength;
        return maxLength;
    }
    
    private Map<Character,Integer> clearCharacters(Map<Character,Integer> map, int startingIndex){
        Iterator<Map.Entry<Character,Integer>> it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Character,Integer> entry = it.next();
            if(entry.getValue() <= startingIndex)
                it.remove();
        }
        return map;
    }
}

class EnhancedSolution{
    
    public int lengthOfLongestSubstring(String s) {
        return s.length() == 0 ? 0 : s.length() == 1 ? 1 : this.findLongestSubString(s);
    }
    
    private int findLongestSubString(String str){
        int maxLength = 0;
        int windowStart = 0, windowEnd = 0;
        Map<Character,Integer> map = new HashMap<>();
        while(windowEnd < str.length()){
            if(this.windowContainsChar(str, windowStart, windowEnd, map)){
                maxLength = maxLength < (windowEnd-windowStart) ? (windowEnd-windowStart) : maxLength;
                windowStart = map.get(str.charAt(windowEnd)) + 1;
                map.put(str.charAt(windowEnd), windowEnd);
                windowEnd++;
            }else{
                map.put(str.charAt(windowEnd), windowEnd);
                windowEnd++;
            }
        }
        return maxLength < (windowEnd-windowStart) ? (windowEnd-windowStart) : maxLength;
    }
    
    private boolean windowContainsChar(String str, int windowStart, int windowEnd, Map<Character,Integer> map){
        return map.containsKey(str.charAt(windowEnd)) && map.get(str.charAt(windowEnd)) >= windowStart && map.get(str.charAt(windowEnd)) <= windowEnd;
    }
}
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "dvdf";
        System.out.println(solution.lengthOfLongestSubstring(s));
        
        EnhancedSolution enhancedSolution = new EnhancedSolution();
        System.out.println(enhancedSolution.lengthOfLongestSubstring(s));
    }
    
}
