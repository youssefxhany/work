/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validpalindromei;

/**
 *
 * @author youssef hany
 */
class Solution {
    public boolean isPalindrome(String s) {
        return s.length() == 0 ? true : this.checkPalindrome(s);
    }
    
    private boolean checkPalindrome(String s){
        StringBuilder str = this.preprocessString(s);
        String preprocessedStr = str.toString();
        String preprocessedStrReversed = str.reverse().toString();
        return preprocessedStr.length() == 0 || preprocessedStr.compareTo(preprocessedStrReversed) == 0;
    }
    
    private StringBuilder preprocessString(String s){
        StringBuilder str = new StringBuilder(s.length());
        for(Character c: s.toCharArray()){
            c = ( ((int) c >= 65) && ((int) c <= 90) ) ? this.lowerCase(c) : c ;
            if(this.isNumber(c) || this.isLetter(c))
                str.append(c);
        }
        return str;
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
    
}

class EnhancedSolution{
    public boolean isPalindrome(String s) {
        return s.length() == 0 ? true : this.checkPalindrome(s);
    }
    
    private boolean checkPalindrome(String s){
        String preprocessed = this.preprocessString(s.trim().replace(" ", ""));
        if(preprocessed.length() == 0) return true;
        int start = 0, end = preprocessed.length()-1;
        while(start < end){
            if(preprocessed.charAt(start) != preprocessed.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
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
}

class MoreEnhanced{
    public boolean isPalindrome(String s) {
        return s.length() == 0 ? true : this.checkPalindrome(s);
    }
    
    private boolean checkPalindrome(String s){
        int start = 0, end = s.length()-1;
        while(start < end){
            if(this.preprocessCharacter(s.charAt(start)) == null){
                start++;
                continue;
            }
            if(this.preprocessCharacter(s.charAt(end)) == null){
                end--;
                continue;
            } 
            if(this.preprocessCharacter(s.charAt(start)) != this.preprocessCharacter(s.charAt(end)))
                return false;
            start++;
            end--;
        }
        return true;
    }
    
    private Character preprocessCharacter(Character c){
         c = ( ((int) c >= 65) && ((int) c <= 90) ) ? this.lowerCase(c) : c ;
         if(this.isNumber(c) || this.isLetter(c))
             return c;
        return null;
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
}
public class ValidPalindromeI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "abb";
        System.out.println(solution.isPalindrome(str));
        
        EnhancedSolution enhancedSolution = new EnhancedSolution();
        System.out.println(enhancedSolution.isPalindrome(str));
        
        MoreEnhanced moreEnhanced = new MoreEnhanced();
        System.out.println(moreEnhanced.isPalindrome(str));
    }
    
}
