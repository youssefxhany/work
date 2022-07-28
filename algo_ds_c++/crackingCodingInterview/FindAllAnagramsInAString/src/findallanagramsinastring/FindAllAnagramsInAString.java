/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package findallanagramsinastring;

/**
 *
 * @author youssef hany
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> results = new ArrayList<>();
        if(s.length() == 0 || p.length() == 0) return results;
        Map<Character,Integer> frequencyMap = this.buildFrequencyMap(p);
        int windowStart = -p.length(), windowEnd = -1;
        while(windowEnd < s.length()){
            if(windowEnd >= 0 && frequencyMap.containsKey(s.charAt(windowEnd)))
                frequencyMap.put(s.charAt(windowEnd),frequencyMap.get(s.charAt(windowEnd))-1);
            if(windowEnd>= 0 && windowStart>= 0 && !frequencyMap.values().stream().anyMatch(v->v!=0))
                results.add(windowStart);
            if(windowStart >= 0 && frequencyMap.containsKey(s.charAt(windowStart)))
                frequencyMap.put(s.charAt(windowStart),frequencyMap.get(s.charAt(windowStart))+1);
            windowEnd++;
            windowStart++;
        }
        return results;
    }

    private Map<Character,Integer> buildFrequencyMap(String str){
        Map<Character,Integer> map = new HashMap<>();
        for(Character c : str.toCharArray()){
            if(!map.containsKey(c))
                map.put(c,1);
            else
                map.put(c,map.get(c)+1);
        }
        return map;
    }
}
public class FindAllAnagramsInAString {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "cbaebabacd", p = "abc";
        System.out.println(solution.findAnagrams(s,p));
    }
    
}
