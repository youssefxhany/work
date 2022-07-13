/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package besttimetobuyandsellstockii;

/**
 *
 * @author youssef hany
 */
class Solution {

    public int maxProfit(int[] prices) {
        int windowStart = 0, windowEnd = 0, profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (this.getWindowLength(windowStart, windowEnd) < 2 && ((i + 1) >= prices.length || prices[i] > prices[i + 1])) {
                windowStart++;
                windowEnd++;
                continue;
            }
            if (this.getWindowLength(windowStart, windowEnd) >= 2 && ((i + 1) >= prices.length || prices[i] > prices[i + 1])) {
                profit += prices[windowEnd] - prices[windowStart];
                windowEnd++;
                windowStart = windowEnd;
                continue;
            }
            windowEnd++;
        }
        return profit;
    }

    private int getWindowLength(int start, int end) {
        return end - start + 1;
    }
}

public class BestTimetoBuyandSellStockII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int prices[] = {3, 3, 5, 0, 0, 3, 1, 4};
        int prices2[] = {7,6,4,3,1,10,20};
        System.out.println(solution.maxProfit(prices2));
    }

}
