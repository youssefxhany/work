/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longestpalindromicsubstring;

/**
 *
 * @author youssef hany
 */
class Solution {
    public String longestPalindrome(String s) {
        if(s.length() == 1 || ( s.length() == 2 && s.charAt(0) != s.charAt(1)) ) {
            return Character.toString(s.charAt(0));
        }
        StringBuilder str = new StringBuilder(s);
        String result = "";
        int start = 0, end = 1, charCounter = 0;
        while (end < s.length()) {
            if(s.charAt(start) == s.charAt(end)){
                charCounter++;
            }
            StringBuilder reversed = new StringBuilder(s.substring(start, end + 1)).reverse();
            int found = str.substring(end).indexOf(reversed.toString());
            if (found != -1) {
                found = end - start + found + 1;
                if (found == end || found == (end + 1)) {
                    String newRes = s.substring(start,found + end );
                    result = result.length() < newRes.length() ? newRes : result;
                    start++;
                    end = start;
                } else {
                    end++;
                }
            } else {
                if (reversed.length() == 2 && reversed.charAt(0) == reversed.charAt(1)) {
                    result = result.length() < reversed.length() ? reversed.toString() : result;
                }
                start++;
                end++;
            }
            
        }
        return charCounter == s.length() ? s : result;
    }
}
public class LongestPalindromicSubstring {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "boynracecarngirlyo";
        String s1 = "babad";
        String s2 = "bbdd";
        String s3 = "ac";
        String s4 = "a";
        String s5 = "bb";
        String s6 = "aaaa";
        String s7 = "caba";
        System.out.println(solution.longestPalindrome(s));
        System.out.println(solution.longestPalindrome(s1));
        System.out.println(solution.longestPalindrome(s2));
        System.out.println(solution.longestPalindrome(s3));
        System.out.println(solution.longestPalindrome(s4));
        System.out.println(solution.longestPalindrome(s5));
        System.out.println(solution.longestPalindrome(s6));
        System.out.println(solution.longestPalindrome(s7));
    }
    
}
