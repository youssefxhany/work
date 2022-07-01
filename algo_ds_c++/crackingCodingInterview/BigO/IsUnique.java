/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isunique;

/**
 *
 * @author youssef hany
 */
//We assume that string is ASCII -> 128 characters
class Solution{
    private final int numPossibleChars = 128;
    
    public boolean solve(String s){
        return s.length() > numPossibleChars ? false : isUnique(s);
    }
    
    private boolean isUnique(String s){
        boolean alphabet[] = new boolean[numPossibleChars];
        for(int i=0; i<s.length(); i++){
            int value = s.charAt(i);
            if(alphabet[value])
                return false;
            alphabet[value] = true;
        }
        return true;
    }
}
public class IsUnique {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abcdefg";
        System.out.println(solution.solve(s));
    }
    
}
