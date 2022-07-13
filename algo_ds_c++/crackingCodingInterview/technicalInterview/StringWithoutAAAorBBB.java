/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringwithoutaaaorbbb;

/**
 *
 * @author youssef hany
 */
//Given two integers a and b, return any string s such that:
//s has length a + b and contains exactly a 'a' letters, and exactly b 'b' letters,
//The substring 'aaa' does not occur in s, and
//The substring 'bbb' does not occur in s.
class Solution {

    public String strWithout3a3b(int a, int b) {
        int bigCounter = a > b ? a : b;
        int lowCounter = a > b ? b : a;
        char bigChar = a > b ? 'a' : 'b';
        char lowChar = a > b ? 'b' : 'a';
        StringBuilder str = new StringBuilder(a + b);
        while (bigCounter >= 2) {
            str.append(Character.toString(bigChar));
            str.append(Character.toString(bigChar));
            if (lowCounter > 0) {
                str.append(lowChar);
                if (bigCounter == lowCounter) {
                    str.append(lowChar);
                    lowCounter--;
                }
                lowCounter--;
            }
            bigCounter -= 2;
        }
        while (bigCounter != 0) {
            str.append(bigChar);
            bigCounter--;
        }
        while (lowCounter != 0) {
            str.append(lowChar);
            lowCounter--;
        }
        return str.toString();
    }
}

class BetterSolution{

    public String strWithout3a3b(int a, int b) {
        StringBuffer str = new StringBuffer(a+b);
        int bigCounter = a > b ? a : b;
        int lowCounter = a > b ? b : a;
        char bigChar = a > b ? 'a' : 'b';
        char lowChar = a > b ? 'b' : 'a';
        str = this.insertNcharsAt(str, lowChar, lowCounter, 0);
        int insertionPosition = 0;
        while(bigCounter>0){
            if(bigCounter>1 && bigCounter>=lowCounter){
                this.insertNcharsAt(str, bigChar, 2, insertionPosition);
                insertionPosition+=3;
                bigCounter-=2;
            }else{
                this.insertNcharsAt(str, bigChar, 1, insertionPosition);
                insertionPosition+=2;
                bigCounter--;
            }
            lowCounter--;
        }
        return str.toString();
    }
    
    private StringBuffer insertNcharsAt(StringBuffer str, char c, int num, int position) {
        StringBuilder strBuilder = new StringBuilder(num);
        while(num>0){
            strBuilder.append(c);
            num--;
        }
        str.insert(position, strBuilder.toString());
        return str;
    }
}

public class StringWithoutAAAorBBB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BetterSolution solution = new BetterSolution();
        int a = 6;
        int b = 3;
        String sol = solution.strWithout3a3b(a, b);
        System.out.println(sol);
        System.out.println(sol.length() == (a + b));
    }

}
