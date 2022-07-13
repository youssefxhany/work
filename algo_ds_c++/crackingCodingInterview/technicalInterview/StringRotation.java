/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringrotation;

/**
 *
 * @author youssef hany
 */
//Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
//
//A shift on s consists of moving the leftmost character of s to the rightmost position.
//
//For example, if s = "abcde", then it will be "bcdea" after one shift.
class Solution {
    public boolean rotateString(String s, String goal) {
        if(s.length() != goal.length())
            return false;
        int pointer = 0;
        for(int i=0; i<goal.length(); i++){
            if(s.charAt(pointer) == goal.charAt(i)){
                pointer++;
                continue;
            }
            if(i>0 && goal.charAt(i) == goal.charAt(i-1))
                continue;
            pointer = 0;
        }
        return goal.contains(s.substring(pointer));
    }
}

class Solution2 {
    public boolean rotateString(String s, String goal) {
        if(s.length() != goal.length())
            return false;
        StringBuilder str = new StringBuilder(s.length()*2);
        str.append(s);
        str.append(s);
        return str.indexOf(goal) != -1;
    }
}
public class StringRotation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String s = "bbbacddceeb";
        String goal = "ceebbbbacdd";
        
        Solution solution = new Solution();
        System.out.println(solution.rotateString(s, goal));
        
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.rotateString(s, goal));
    }
    
}
