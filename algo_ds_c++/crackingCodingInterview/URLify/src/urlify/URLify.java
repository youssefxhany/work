/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urlify;

import java.util.Arrays;

/**
 *
 * @author youssef hany
 */
class Solution {

    public String solve(String str, int trueLength) {
        return str.length() == 0 ? "" : this.urLify(str.toCharArray(), trueLength);
    }

    private String urLify(char[] str, int trueLength) {
        int numSpaces = this.getNumOccur(str, 0, trueLength, ' ');
        int startEditIndex = trueLength - 1 + numSpaces * 2;
        if (startEditIndex + 1 > str.length) {
            str[startEditIndex + 1] = '\0';
        }
        for (int i = trueLength - 1; i >= 0; i--) {
            if(str[i] == ' '){
                Character characters[] = {'0','2','%'};
                startEditIndex = this.insertCharacters(str,startEditIndex,characters);
            }else{
                str[startEditIndex--] = str[i];
            }
        }
        return Arrays.toString(str);
    }

    private int getNumOccur(char[] str, int start, int end, char target) {
        int counter = 0;
        for (int i = start; i < end; i++) {
            if (str[i] == target) {
                counter++;
            }
        }
        return counter;
    }
    
    private int insertCharacters(char[] str, int startIndex, Character[] characters){
        for(Character c: characters)
            str[startIndex--] = c;
        return startIndex ;
    }
}
public class URLify {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String str = "Mr John Smith    ";
        Solution solution = new Solution();
        System.out.println(solution.solve(str, 13));
    }
    
}
