import java.nio.Buffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC121 {
    public static void main(String[] args) {
        System.out.println("Stock Serializes");
        maxProfit1(new int[]{7, 1, 5, 3, 6, 4});
        maxProfit2(new int[]{7, 1, 5, 3, 6, 4});
        maxProfit3(new int[]{3, 3, 5, 0, 0, 3, 1, 4});
        maxProfit4(new int[]{3, 2, 6, 5, 0, 3}, 2);
        maxProfit5(new int[]{1, 2, 3, 0, 2});
        CircularBuffer cb = new CircularBuffer(5);
        cb.incr(1);
        cb.incr(3);
        sameLetterAndDigit("abcde123");
        sameLetterAndDigit("123abc");
    }
    public static void sameLetterAndDigit(String s) {
        Map<Integer, Integer> m = new HashMap<>();
        int sum = 0;
        String res = "";
        m.put(0, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sum += Character.isDigit(c) ? -1 : 1;
            if (m.get(sum) != null) {
                if (i - m.get(sum) - 1 > res.length()) {
                    res = s.substring(m.get(sum) + 1,i + 1);
                }
            } else {
                m.put(sum, i);
            }
        }
        System.out.println(res);

    }
    static class CircularBuffer {
        private int[] buffer;
        private int cur = 0;
        private int lastTime = 0;
        private int capacity;
        public CircularBuffer(int capacity) {
            this.buffer = new int[capacity];
            this.capacity = capacity;
        }
        public void incr(int time) {
            int next = time % capacity;
            int end = cur + (time - lastTime);
            if (time - lastTime > capacity) {
                end = cur + capacity;
            }
            for (int i = cur; i < end; i++) {
                System.out.println("clear:" + (i + capacity - capacity) % capacity);
            }
            cur = next;
            lastTime = time;
            System.out.println("+++++++++++++++++");
        }
    }
    public static void maxProfit1(int[] prices) {
        int min = Integer.MAX_VALUE, res = 0;
        for (int price : prices) {
            min = Math.min(price, min);
            res = Math.max(res, price - min);
        }
        System.out.println(res);
    }
    public static void maxProfit2(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            res += Math.max(prices[i + 1] - prices[i], 0);
        }
        System.out.println(res);
    }
    public static void maxProfit3(int[] prices) {
        int[] former = new int[prices.length];
        int min = Integer.MAX_VALUE;
        int maxPro = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            maxPro = Math.max(maxPro, prices[i] - min);
            former[i] = maxPro;
        }
        int max = 0, res = 0;
        maxPro = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            maxPro = Math.max(maxPro, max - prices[i]);
            res = Math.max(res, maxPro + former[i]);
        }
        System.out.println(res);
    }
    public static void maxProfit4(int[] prices, int k) {
        int[] buy = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        int[] sell = new int[k + 1];
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j - 1] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j] + prices[i]);
            }
        }
        System.out.println(sell[k]);
    }
    public static void maxProfit5(int[] prices) {
        int buy = Integer.MIN_VALUE;
        int sell = 0, rest = 0, preBuy = buy, preSell = sell, preRest = rest;
        for (int i = 0; i < prices.length; i++) {
            buy = Math.max(preBuy, preRest - prices[i]);
            sell = Math.max(preSell, preBuy + prices[i]);
            rest = Math.max(Math.max(preSell, preBuy), preRest);
            preBuy = buy;
            preSell = sell;
            preRest = rest;
        }
        System.out.println(sell);
    }
}
//    Median of Two Sorted Arrays * (#4)
//        Longest Palindromic Substring (#5)
//        Valid Sudoku (#36)
//        Combination Sum (#39)
//        Permutations (#46)
//        Rotate List (#61)
//        Convert Sorted List to Binary Search Tree (#109)
//        Populating Next Right Pointers in Each Node (#116)
//        Game of Life (#289)
//        Find Median from Data Stream (#295)
//        Longest Increasing Subsequence (#300)
//        Design Tic-Tac-Toe (#348)
//        Pacific Atlantic Water Flow (#417)
//        Minesweeper (#529)
//json parser：input是json string，value只有数字. parse 出一个Map，json可能有很多层的那种
