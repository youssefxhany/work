/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getabcd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author youssef hany
 */
class Solution {

    private final int n;
    private int numberOfPairs = 0;

    public Solution(int n) {
        this.n = n;
    }

    public void printAbcd() {
        Map<Double, List<String>> map = this.generateMap();
        this.printMapKeys(map);
        System.out.println("Number of pairs is: " + numberOfPairs);
    }

    private Map<Double, List<String>> generateMap() {
        Map<Double, List<String>> map = new HashMap<>();
        for (int a = 0; a < this.n; a++) {
            for (int b = 0; b < this.n; b++) {
                Double result = Math.pow(a, 3) + Math.pow(b, 3);
                String pair = "(" + a + "," + b + ")";
                if (!map.containsKey(result)) {
                    map.put(result, Arrays.asList(pair));
                }else{
                    this.createBiggerList(map, result, a , b);
                }
            }
        }
        return map;
    }
    
    private void createBiggerList(Map<Double, List<String>> map, Double key, int a, int b) {
        String olderPair = map.get(key).get(0);
        String numbers[] = olderPair.split(",");
        int num1 = Integer.parseInt(numbers[0].substring(1));
        int num2 = Integer.parseInt(numbers[1].substring(0,numbers[1].length()-1));
        if (!(num1 == a && num2 == b || num1 == b && num2 == a)) {
            String newPair = "(" + a + "," + b + ")";
            map.put(key, Arrays.asList(olderPair, newPair));
        }
    }

    private void printMapKeys(Map<Double,List<String>> map){
        for(Double result: map.keySet())
            if(map.get(result).size() == 2){
                System.out.println("Pairs are: " + map.get(result).get(0) + " and " + map.get(result).get(1));
                numberOfPairs++;
            }
    }
}

public class GetABCD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution(1000);
        solution.printAbcd();
    }

}
