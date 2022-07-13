/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package romantointeger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class Solution {
    public int romanToInt(String s) {
        int number = 0;
        Map<Character, Integer> symbolMap = this.buildSymbolMap();
        for (int i = 0; i < s.length(); i++) {
            if ((i + 1) < s.length() && symbolMap.get(s.charAt(i)) < symbolMap.get(s.charAt(i + 1))) {
                number -= symbolMap.get(s.charAt(i));
                continue;
            }
            number += symbolMap.get(s.charAt(i));
        }
        return number;
    }
    private Map<Character,Integer> buildSymbolMap(){
        Map<Character,Integer> symbolMap = new HashMap<>();
        Character symbolList[] = {'I','V','X','L','C','D','M'};
        for(int i=0; i<symbolList.length; i++)
            symbolMap.put(symbolList[i], (i == 0) ? 1 : (i %2 == 0) ? symbolMap.get(symbolList[i-1])*2 : symbolMap.get(symbolList[i-1])*5);
        return symbolMap;
    }
}
public class RomanToInteger {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String roman = "III";
        System.out.println(solution.romanToInt(roman));
    }
    
}
