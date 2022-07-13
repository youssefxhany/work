/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Leetcode266;

/**
 *
 * @author youssef hany
 */
//Given a string, determine if a permutation of the string could form a palindrome.
//For example,
//"code" -> False, "aab" -> True, "carerac" -> True.
//Hint:
//Consider the palindromes of odd vs even length. What difference do you notice?
//Count the frequency of each character.
//If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
class Solution {
    public boolean isPalindrome(String s) {
        return s.length() == 0 ? true : this.checkPalindrome(s);
    }
    
    private boolean checkPalindrome(String s){
        String preprocessedStr = this.preprocessString(s);
        if(preprocessedStr.length() == 0) return true;
        Long frequencyMap = this.buildFrequencyMap(preprocessedStr);
        return ((frequencyMap - 1) & (frequencyMap)) == 0;
    }
    
    private String preprocessString(String s){
        StringBuilder str = new StringBuilder(s.length());
        for(Character c: s.toCharArray()){
            c = ( ((int) c >= 65) && ((int) c <= 90) ) ? this.lowerCase(c) : c ;
            if(this.isNumber(c) || this.isLetter(c))
                str.append(c);
        }
        return str.toString();
    }
    
    private Character lowerCase(Character c){
        return (char) ((int) c + 32);
    }
    
    private boolean isNumber(Character c){
        return ((int) c >= 48) && ((int) c <= 57);
    }
    
    private boolean isLetter(Character c){
        return ((int) c >= 97) && ((int) c <= 122);
    }
    
    private Long buildFrequencyMap(String str){
        Long frequencyMap = 0L;
        for(Character c : str.toCharArray()){
            frequencyMap = this.toggle(frequencyMap, ( ((int) c >= 48) && ((int) c <= 57) ) ? ((int)c) - 48 : ((int)c) - 87 );
        }
        return frequencyMap;
    }
    
    private Long toggle(Long frequencyMap, int index){
        int x = 1 << index;
        return frequencyMap ^= x;
    }
}
public class Leetcode266 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "";
        System.out.println(solution.isPalindrome(str));
    }
    
}
