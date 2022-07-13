/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oneaway;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class Solution {

    public boolean solve(String str1, String str2) {
        return Math.abs(str1.length() - str2.length()) > 1 ? false : this.isOneEditAway(str1, str2);
    }

    private boolean isOneEditAway(String str1, String str2) {
        Map<Character, Integer> mapStr1 = this.buildFrequencyMap(str1.length() >= str2.length() ? str1 : str2);
        int pointer1 = 0, pointer2 = 0;
        while (pointer1 < str1.length() && pointer2 < str2.length()) {
            if (str1.charAt(pointer1) == str2.charAt(pointer2)) {
                if (mapStr1.get(str1.charAt(pointer1)) == 1) {
                    mapStr1.remove(str1.charAt(pointer1));
                } else {
                    mapStr1.put(str1.charAt(pointer1), mapStr1.get(str1.charAt(pointer1)) - 1);
                }
                pointer1++;
                pointer2++;
            } else {
                if (mapStr1.containsKey(str1.length() >= str2.length() ? str2.charAt(pointer2) : str1.charAt(pointer1))) {
                    if (str1.length() >= str2.length()) {
                        pointer1++;
                    } else {
                        pointer2++;
                    }
                } else {
                    pointer1++;
                    pointer2++;
                }
            }
            if (Math.abs(pointer2 - pointer1) > 1) {
                return false;
            }
        }
        return mapStr1.values().stream().reduce(0, Integer::sum).intValue() <= 1;
    }

    private Map<Character, Integer> buildFrequencyMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : str.toCharArray()) {
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }
        return map;
    }
}

class BetterSolution {

    public boolean solve(String str1, String str2) {
        return this.determineType(str1, str2);
    }

    private boolean determineType(String str1, String str2) {
        if (str1.length() == str2.length()) {
            return this.oneEditReplace(str1, str2);
        } else if (str1.length() + 1 == str2.length()) {
            return this.oneEditInsert(str1, str2);
        } else if (str1.length() - 1 == str2.length()) {
            return this.oneEditInsert(str2, str1);
        }
        return false;
    }

    private boolean oneEditReplace(String str1, String str2) {
        boolean oneEditAway = false;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (oneEditAway) {
                    return false;
                }
                oneEditAway = true;
            }
        }
        return true;
    }

    //str1 is always the shortest string
    private boolean oneEditInsert(String str1, String str2) {
        int pointer1 = 0, pointer2 = 0;
        while (pointer1 < str1.length() && pointer2 < str2.length()) {
            if (str1.charAt(pointer1) != str2.charAt(pointer2)) {
                pointer2++;
                if (Math.abs(pointer2 - pointer1) > 1) {
                    return false;
                }
            } else {
                pointer1++;
                pointer2++;
            }
        }
        return true;
    }
}

class CompactSolution {

    public boolean solve(String str1, String str2) {
        return Math.abs(str1.length() - str2.length()) > 1 ? false : thisIsOneEditAway(str1, str2);
    }

    private boolean thisIsOneEditAway(String str1, String str2) {
        String shortestString = str1.length() < str2.length() ? str1 : str2;
        String longestString = str1.length() < str2.length() ? str2 : str1;
        int p1 = 0, p2 = 0;
        boolean oneEditAway = false;
        while (p1 < shortestString.length() && p2 < longestString.length()) {
            if (shortestString.charAt(p1) != longestString.charAt(p2)) {
                if (oneEditAway) {
                    return false;
                }
                oneEditAway = true;
                if (shortestString.length() == longestString.length()) {
                    p1++;
                }
            } else {
                p1++;
            }
            p2++;
        }
        return true;
    }
}

public class OneEditAway {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String str1 = "aaat";
        String str2 = "aagt";
        System.out.println(solution.solve(str1, str2));

        BetterSolution betterSolution = new BetterSolution();
        System.out.println(betterSolution.solve(str1, str2));

        CompactSolution compactSolution = new CompactSolution();
        System.out.println(compactSolution.solve(str1, str2));
    }

}
