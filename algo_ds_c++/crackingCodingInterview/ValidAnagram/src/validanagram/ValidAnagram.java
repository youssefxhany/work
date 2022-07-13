/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validanagram;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        Map<Character,Integer> frequencyMap = this.buildFrequencyMap(s);
        for(Character c : t.toCharArray())
            if(frequencyMap.containsKey(c)){
                if(frequencyMap.get(c) > 1)
                    frequencyMap.put(c,frequencyMap.get(c)-1);
                else 
                    frequencyMap.remove(c);
            }else{
                return false;
            }
        return frequencyMap.isEmpty();
    }
    
    private Map<Character,Integer> buildFrequencyMap(String str){
        Map<Character,Integer> frequencyMap = new HashMap<>(26);
        for(Character c: str.toCharArray())
            frequencyMap.put(c, frequencyMap.containsKey(c) ? frequencyMap.get(c) + 1 : 1);
        return frequencyMap;
    }
}

class EnhancedSolution{
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        int frequencyMap[] = this.buildFrequencyMap(s);
        int count = frequencyMap[26];
        for(Character c : t.toCharArray()){
            if(frequencyMap[(int)c -97] > 0){
                frequencyMap[(int)c-97]--;
                if(frequencyMap[(int)c-97] == 0)
                    count--;
            }else{
                return false;
            }
        }
        return count == 0;
    }
    
    private int[] buildFrequencyMap(String str){
        int count = 0;
        int[] frequencyMap = new int[27];
        for(Character c: str.toCharArray()){
            if(frequencyMap[(int)c - 97] == 0)
                count++;
            frequencyMap[(int)c - 97]++;
        }
        frequencyMap[26] = count;
        return frequencyMap;
    }
    
    private void printFreqMap(int freqMap[]){
        for(int i=0; i< freqMap.length-1; i++){
            if(freqMap[i] > 0)
            System.out.println((char)(i+97) + "  :  " + freqMap[i]);
        }
    }
}


public class ValidAnagram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EnhancedSolution solution = new EnhancedSolution();
        String s = "xaaddy";
        String t = "xbbccy";
        System.out.println(solution.isAnagram(s, t));
    }
    
}
