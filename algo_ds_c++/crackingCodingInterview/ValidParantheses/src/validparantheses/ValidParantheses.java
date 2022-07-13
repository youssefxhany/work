/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validparantheses;

import java.util.Stack;

/**
 *
 * @author youssef hany
 */
class Solution {

    public boolean isValid(String s) {
        Stack<Character> s1 = this.buildStack(s);
        Stack<Character> s2 = new Stack<>();
        while (!s1.isEmpty()) {
            if (s2.isEmpty() || !this.isInverseBracket(s1.peek(), s2.peek())) {
                s2.push(s1.pop());
            } else {
                s1.pop();
                s2.pop();
            }
        }
        return s1.isEmpty() && s2.isEmpty();
    }

    private Stack<Character> buildStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            stack.push(c);
        }
        return stack;
    }

    private boolean isInverseBracket(Character c1, Character c2) {
        if (c1 == '(' && c2 == ')') {
            return true;
        } else if (c1 == '[' && c2 == ']') {
            return true;
        } else if (c1 == '{' && c2 == '}') {
            return true;
        } else {
            return false;
        }
    }
}

public class ValidParantheses {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "{{{(([]))}}}";
        System.out.println(solution.isValid(s));
    }

}
