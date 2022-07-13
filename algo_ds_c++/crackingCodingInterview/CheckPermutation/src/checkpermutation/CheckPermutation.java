/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkpermutation;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class Solution{
    
    public boolean solve(String s1, String s2){
        return s1.length() != s2.length() || s1.length() == 0 ? false : this.isPermutation(s1, s2);
    }
    
    private boolean isPermutation(String s1, String s2){
        Map<Character,Integer> map = this.buildFrequencyMap(s1);
        for(int i=0; i<s2.length(); i++){
            char key = s2.charAt(i);
            if(map.containsKey(key) && map.get(key) > 1)
                map.put(key, map.get(key) - 1);
            else if(map.containsKey(key) && map.get(key) == 1)
                map.remove(key);
            else 
                return false;
        }
        return map.isEmpty();
    }
    
    private Map<Character,Integer> buildFrequencyMap(String s){
        Map<Character,Integer> map = new HashMap<>();
        for(int i=0; i<s.length(); i++)
            map.put(s.charAt(i), map.containsKey(s.charAt(i)) ? (map.get(s.charAt(i)) + 1) : 1);
        return map;
    }
}
public class CheckPermutation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solve("abbc", "babb"));
    }
    
}
