/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttimetobuyandsellstock;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author youssef hany
 */
class Solution1 {
    public int maxProfit(int[] prices) {
        int profit = 0;
        TreeSet<Integer> pricesTree = new TreeSet<>();
        Map<Integer,Integer> pricesMap = new HashMap<>();
        this.buildTreeAndMap(prices, pricesTree, pricesMap);
        for(int price: prices){
            pricesTree.remove(price);
            this.lowerFrequency(pricesMap, price);
            if(pricesMap.containsKey(price))
                pricesTree.add(price);
            if (!pricesTree.isEmpty()) {
                int max = pricesTree.last();
                profit = max - price > profit ? max - price : profit;
            }
        }
        return profit;
    }
    
    private void buildTreeAndMap(int[] prices, TreeSet<Integer> tree, Map<Integer,Integer> map){
        for(int price: prices){
            tree.add(price);
            map.put(price, map.containsKey(price) ? map.get(price) + 1 : 1);
        }
        return;
    }
    
    private void lowerFrequency(Map<Integer,Integer> map, Integer key){
        if(map.containsKey(key)){
            if(map.get(key) > 1)
                map.put(key, map.get(key)-1);
            else
                map.remove(key);
        }
    }
}

class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length <= 1) return 0;
        int min = prices[0] > prices[1] ? prices[1] : prices[0];
        int profit = prices[1] - prices[0] > 0 ? prices[1] - prices[0] : 0;
        for(int i=2; i<prices.length; i++){
            profit = profit < prices[i] - min ? prices[i] - min : profit;
            min = min > prices[i] ? prices[i] : min;
        }
        return profit;
    }
}
public class BestTimetoBuyandSellStock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int prices[] = {7,1,5,3,6,4};
        
        Solution1 solution1 = new Solution1();
        System.out.println(solution1.maxProfit(prices));
        
        Solution solution = new Solution();
        System.out.println(solution.maxProfit(prices));
    }
    
}
