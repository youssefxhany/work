/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permutationinstring;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author youssef hany
 */

class SolutionOrderS1S2 {
    public boolean checkInclusion(String s1, String s2) {
        return s1.length() > s2.length() || s1.length() == 0 || s2.length() == 0 ? false : this.hasPermutation(s1, s2);
    }
    
    private boolean hasPermutation(String s1, String s2) {
        int windowStart = 0, windowEnd = s1.length() - 1;
        while (windowEnd < s2.length()) {
            Map<Character, Integer> mapString1 = this.generateMap(s1);
            for (int i = windowStart; i <= windowEnd; i++) {
                if (mapString1.containsKey(s2.charAt(i))) {
                    if (mapString1.get(s2.charAt(i)) == 1) {
                        mapString1.remove(s2.charAt(i));
                    } else {
                        mapString1.put(s2.charAt(i), mapString1.get(s2.charAt(i)) - 1);
                    }
                }else{
                    break;
                }
            }
            if(mapString1.isEmpty())
                return true;
            windowStart++;
            windowEnd++;
        }
        return false;
    }
    
    private Map<Character, Integer> generateMap(String string) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < string.length(); i++) 
            map.put(string.charAt(i),map.containsKey(string.charAt(i)) ? map.get(string.charAt(i)) + 1 : 1);
        return map;
    }
        
}

//Better solution at O(s2)
class Solution{
    private int windowStart;
    private int windowEnd;
    
    public boolean checkInclusion(String s1, String s2) {
        windowStart = 0 - s1.length(); 
        windowEnd = -1;
        return s1.length() > s2.length() || s1.length() == 0 || s2.length() == 0 ? false : this.hasPermutation(s1, s2);
    }
    
    private boolean hasPermutation(String s1, String s2) {
        Map<Character,Integer> mapS1 = this.generateMap(s1);
        while(windowEnd < s2.length()-1){
            this.moveWindowToTheRight();
            char enteringChar = s2.charAt(windowEnd);
            char leavingChar = s2.charAt(windowStart >=1 ? windowStart-1 : 0);
            if(mapS1.containsKey(enteringChar))
                mapS1.put(enteringChar, mapS1.get(enteringChar)-1);
            if(windowStart >= 1 && mapS1.containsKey(leavingChar))
                mapS1.put(leavingChar, mapS1.get(leavingChar)+1);
            if(!mapS1.values().stream().filter(v->v!=0).findAny().isPresent()){
                return true;
            }
        }
        return false;
    }
    
    private void moveWindowToTheRight(){
        windowStart++;
        windowEnd++;
    }
    
    private Map<Character, Integer> generateMap(String string) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<string.length(); i++)
            map.put(string.charAt(i), map.containsKey(string.charAt(i)) ? map.get(string.charAt(i)) + 1 : 1);
        return map;
    }
    
}

public class PermutationInString {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkInclusion("adc","dcda"));
    }
    
}
