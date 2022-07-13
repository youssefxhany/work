/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttimetobuyandsellstockwithcooldown;

/**
 *
 * @author youssef hany
 */
class Solution {

    public int maxProfit(int[] prices) {
        int profit = 0, windowStart = 0, windowEnd = 0;
        boolean cooldown = false;
        for (int i = 0; i < prices.length; i++) {
        }
        return profit;
    }

    private int getWindowLength(int start, int end) {
        return end - start + 1;
    }

    private boolean hasToCooldown(int[] prices, int index) {
        return true;
    }
}

public class BestTimeToBuyAndSellStockWithCooldown {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int prices[] = {1,2,3,0,2,3,1,8,1,100};
        System.out.println(solution.maxProfit(prices));
    }

}
