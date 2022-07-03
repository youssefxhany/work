/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindromepermutation;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author youssef hany
 */
class Solution{
    private int oddKeyCounter;
    
    public Solution(){
        this.oddKeyCounter = 0;
    }
    
    public boolean solve(String str){
        return str.length() == 0 ? false : this.hasPalindromePermutation(this.preProcess(str));
    }
    
    private boolean hasPalindromePermutation(String str){
        Map<Character,Integer> strMap = this.buildFrequencyMap(str);
        return this.hasMoreThanOneOddKeys(strMap);
    }
    
    private Map<Character,Integer> buildFrequencyMap(String str){
        Map<Character,Integer> map = new HashMap<>();
        for(Character c: str.toCharArray())
            map.put(c, map.containsKey(c) ? map.get(c)+1 : 1);
        return map;
    }
    
    private String preProcess(String str){
        StringBuilder newString = new StringBuilder();
        for(int i=0; i<str.length(); i++){
            char c = (int) str.charAt(i) >= 65 && (int) str.charAt(i) <=91  ? (char) (str.charAt(i) + 32) : str.charAt(i);
            if((int) c >= 97 && (int) c <= 123)
                newString.append(c);
        }   
        return newString.toString();
    }
    
    private boolean hasMoreThanOneOddKeys(Map<Character,Integer> map){
        for(Character c : map.keySet()){
            if(map.get(c) % 2 != 0)
                this.oddKeyCounter++;
            if(this.oddKeyCounter > 1)
                return false;
        }
        return true;
    }
}

class OtherSolution{
    public boolean solve(String str){
        return str.length() == 0 ? false : this.hasPalindromePermutation(this.preProcess(str));
    }
    
    private boolean hasPalindromePermutation(String str){
        int vector = this.buildBitVector(str);
        return ((vector-1) & vector) == 0;
    }
    
    private Character lowerCase(Character c){
        return (char) ((int) c + 32);
    }
    
    private String preProcess(String str){
        StringBuilder newStr = new StringBuilder();
        for (Character c : str.toCharArray()) {
            int a = (char) 'a';
            int z = (char) 'z';
            c = ((int) c >= (a - 32)) && ((int) c <= (z - 32)) ? this.lowerCase(c) : c;
            if((int) c >= a && (int) c <= z)
                newStr.append((char) c);
        }
        return newStr.toString();
    }
    
    private int toggle(int vector, int index){
        int x = 1 << index;
        return vector ^= x;
    }
    
    private int buildBitVector(String str){
        int vector = 0;
        for(Character c: str.toCharArray()){
            int charIndex = Character.getNumericValue(c) - Character.getNumericValue('a');
            vector = this.toggle(vector, charIndex);
        }
        return vector;
    }
}
public class PalindromePermutation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String str = "Tact Coa";
        Solution solution = new Solution();
        System.out.println(solution.solve(str));
        
        OtherSolution otherSolution = new OtherSolution();
        System.out.println(otherSolution.solve(str));
    }
    
}
