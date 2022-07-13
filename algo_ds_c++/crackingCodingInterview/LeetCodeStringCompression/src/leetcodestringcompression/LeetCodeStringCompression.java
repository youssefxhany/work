/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcodestringcompression;

/**
 *
 * @author youssef hany
 */
//Given an array of characters chars, compress it using the following algorithm:
//
//Begin with an empty string s. For each group of consecutive repeating characters in chars:
//
//If the group's length is 1, append the character to s.
//Otherwise, append the character followed by the group's length.
//The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
//
//After you are done modifying the input array, return the new length of the array.
//
//You must write an algorithm that uses only constant extra space.

class Solution {
    public int compress(char[] chars) {
        return chars.length == 0 ? 0 : this.getCompressionLength(chars);
    }
    
    private int getCompressionLength(char[] chars) {
        int editIndex = 0, windowStart = 0, windowEnd = 0;
        for (int i = 0; i < chars.length; i++) {
            if ((i + 1) < chars.length && chars[i + 1] == chars[i]) {
                windowEnd++;
            } else {
                chars[editIndex++] = chars[i];
                if ((windowEnd - windowStart + 1) > 1) {
                    editIndex = this.addWindowLengthToChars((windowEnd - windowStart + 1), editIndex, chars);
                }
                windowStart = windowEnd = (i + 1);
            }
        }
        return editIndex ;
    }
    
    private int addWindowLengthToChars(int numDuplications, int startingIndex, char[] chars){
        int index = startingIndex + Integer.toString(numDuplications).length() -1;
        while(numDuplications > 0){
            char c = (char) (numDuplications%10 + 48);
            chars[index--] = c;
            startingIndex++;
            numDuplications = numDuplications / 10;
        }
        return startingIndex;
    }
}
public class LeetCodeStringCompression {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        char chars[] = {'a','b','b','b','b','b','b','b','b','b','b','b','b','c','c','c'};
        System.out.println(solution.compress(chars));
    }
    
}
