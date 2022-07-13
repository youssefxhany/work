/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringcompression;

/**
 *
 * @author youssef hany
 */
class Solution{
    private int windowStart;
    private int windowEnd;
    
    public Solution(){
        this.resestWindow(0);
    }
    
    public String solve(String str){
        String compressed = this.compress(str);
        return str.length() > compressed.length() ? compressed : str;
    }
    
    private String compress(String str){
        StringBuilder compressed = new StringBuilder();
        for(int i=0 ; i<str.length(); i++){
            if((i+1)<str.length() && str.charAt(i) == str.charAt(i+1))
                this.extendWindow();
            else{
                compressed.append((char)str.charAt(i));
                compressed.append(this.getWindowLength());
                this.resestWindow(i+1);
            }
        }
        return compressed.toString();
    }
    
    private void extendWindow(){
        this.windowEnd++;
    }
    
    private void resestWindow(int value){
        this.windowStart = this.windowEnd = value;
    }
    
    private int getWindowLength(){
        return this.windowEnd - this.windowStart + 1;
    }
}
public class StringCompression {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solve("abcdeeeeeeeeeeeeeee"));
    }
    
}
