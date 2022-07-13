/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindromepermutationii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author youssef hany
 */
//267. Palindrome Permutation II
//Problem:
//Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
//For example:
//Given s = "aabb", return ["abba", "baab"].
//Given s = "abc", return [].
class Solution {
    private String oddChar = "";
    
    public List<String> solve(String str) {
        return str.length() == 0 ? new ArrayList<>() : this.getPalindromicPermutations(this.preProcess(str));
    }

    private String preProcess(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : str.toCharArray()) {
            int a = (int) 'a';
            int z = (int) 'z';
            c = ((int) c >= a - 32) && ((int) c <= z - 32) ? this.lowerCase(c) : c;
            if (((int) c >= a) && ((int) c <= z)) {
                stringBuilder.append((char) c);
            }
        }
        return stringBuilder.toString();
    }

    private Character lowerCase(Character c) {
        return (char) ((int) c + 32);
    }

    private List<String> getPalindromicPermutations(String str) {
        List<String> palindromicPermutations = new ArrayList<>();
        Map<Character, Integer> frequencyMap = this.generateFrequencyMap(str);
        if (!hasPalindromicPermutations(frequencyMap)) {
            return palindromicPermutations;
        }
        String mainString = this.generateMainString(frequencyMap);
        List<String> permutations = this.getPermutations(mainString);
        palindromicPermutations = this.generateResults(permutations);
        return palindromicPermutations;
    }

    private Map<Character, Integer> generateFrequencyMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : str.toCharArray()) {
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }
        return map;
    }

    private boolean hasPalindromicPermutations(Map<Character, Integer> frequencyMap) {
        boolean oddFlag = false;
        for (Character key : frequencyMap.keySet()) {
            if (frequencyMap.get(key) % 2 == 1) {
                if (oddFlag) {
                    return false;
                }
                oddFlag = true;
                oddChar = key.toString();
            }
        }
        return true;
    }

    private String generateMainString(Map<Character, Integer> frequencyMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : frequencyMap.keySet()) {
            stringBuilder = stringBuilder.append(this.getMultiCharString(c, frequencyMap.get(c)/2));
        }
        return stringBuilder.toString();
    }

    private String getMultiCharString(Character c, int length) {
        if (length == 0) {
            return "";
        }
        return c.toString() + getMultiCharString(c, length - 1);
    }
    
    private List<String> getPermutations(String str){
        if(str.length() == 1)
            return Arrays.asList(str);
        return this.insertCharAtEveryPosition(getPermutations(str.substring(0, str.length()-1)),str.charAt(str.length()-1));
    }
    
    private List<String> insertCharAtEveryPosition(List<String> list, Character c){
        List<String> permutations = new ArrayList<>();
        int end = list.get(0).length() == 1 ? 1 : this.getFactorial(list.get(0).length())/2;
        for(int i=0; i < end ; i++){
            String str = list.get(i);
            for(int j=0; j<= str.length();j++){
                String perm = str.substring(0, j) + c + str.substring(j);
                permutations.add(perm);
            }
        }
        return permutations;
    }
    
    private int getFactorial(int num){
        if(num == 0 || num == 1)
            return 1;
        return num * getFactorial(num-1);
    }
    
    private List<String> generateResults(List<String> str){
        Set<String> results = new HashSet<>();
        for(String perm: str){
            results.add(perm + this.oddChar + this.reverseString(perm));
            results.add(this.reverseString(perm) + this.oddChar + perm);
        }
        return new ArrayList<>(results);
    }
    
    private String reverseString(String str){
        return new StringBuilder(str).reverse().toString();
    }
}

public class PalindromePermutationII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "aabbcc";
        System.out.println(solution.solve(str));
    }

}
