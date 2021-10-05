import java.util.Arrays;

public class LC518 {
    public static void main(String[] args) {
        change( 5, new int[]{1, 2, 5});
        coinChange(new int[]{1, 2, 5}, 11);
    }
    public static void change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
            }
        }
        System.out.println(dp[coins.length][amount]);
    }
    public static void coinChange(int[]coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                dp[j][i] = i >= coins[j] ? dp[j][i - coins[j]] + 1 : dp[j - 1][i];
            }
        }
        System.out.println(dp[coins.length - 1][amount]);
    }
}
