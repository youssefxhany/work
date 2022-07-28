/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repeateddnasequences;

/**
 *
 * @author youssef hany
 */
import java.util.*;

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> result = new HashSet<>();
        if(s.length() < 10) return Collections.emptyList();
        Set<String> dnaSet = new HashSet<>();
        int windowStart = 0, windowEnd = 9;
        while(windowEnd < s.length()){
            String dna = s.substring(windowStart,windowEnd+1);
            if(dnaSet.contains(dna)){
                result.add(dna);
            }else{
                dnaSet.add(dna);
            }
            windowEnd++;
            windowStart++;
        }
        List<String> arr = new ArrayList();
        arr.addAll(result);
        return arr;
    }
}
public class RepeatedDNASequences {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTTAAAAGGGTTT";
        System.out.println(solution.findRepeatedDnaSequences(s));
    }
    
}
