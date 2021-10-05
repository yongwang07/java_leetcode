import java.util.*;

public class LC45 {
    public static void main(String[] args) {
//        canJump(new int[]{2, 3, 1, 1, 4});
//        canJump(new int[]{3, 2, 1, 0, 4});
        coinChange(new int[]{1, 2, 5}, 11);
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.value - b.value);
        Node a = new Node(19);
        Node b = new Node(11);
        q.offer(a);
        q.offer(b);
        System.out.println(q);
        q.contains(b);
        TreeMap<Integer, String> freq = new TreeMap<>((x, y) -> y.intValue() - x.intValue());
        freq.put(100, "100");
        freq.put(200, "200");
        freq.put(300, "300");
        System.out.println(freq);
        freq.remove(200);
        freq.put(120, "120");
        for (int f : freq.keySet()) {
            System.out.println(">>>" + f);
        }
        System.out.println(oneChange("abc", "abd"));
    }
    static class Node {
        int value;
        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
    public static boolean oneChange(String a, String b) {
        boolean hasChanged = false;
        int i = 0, j = 0;
        if (Math.abs(a.length() - b.length()) > 1) return false;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) != b.charAt(j)) {
                i++;
                j++;
                continue;
            }
            if (hasChanged) return false;
            if (a.length() > b.length()) {
                i++;
            } else if (a.length() < b.length()) {
                j++;
            } else {
                i++;
                j++;
            }
            hasChanged = true;
        }
        return true;
    }
    public static void coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int[] pre = new int[amount + 1];
        pre[0] = -1;
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    if (dp[i - coins[j]] + 1 < dp[i]) {
                        pre[i] = i - coins[j];
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(pre));
        List<Integer> res = new ArrayList<>();
        int i = amount;
        while (amount > 0) {
            res.add(amount - pre[i]);
            amount = pre[i];
            i = pre[i];
        }
        System.out.println(res);
    }
    public static void canJump(int[] nums) {
        int[] dp = new int[nums.length];
        int[] pre = new int[nums.length];
        dp[0] = 0;
        pre[0] = -1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 100;
            for (int j = 0; j < i; j++) {
                if (nums[j] + j >= i) {
                    if (dp[j] + 1 < dp[i]) {
                        dp[i] = dp[j] + 1;
                        pre[i] = j;
                    }
                }
            }
        }
        if (dp[dp.length - 1] == 100) {
            System.out.println("un reach");
            return;
        }
        List<Integer> res = new ArrayList<>();
        res.add(dp.length - 1);
        int i = pre[dp.length - 1];
        while (i != -1) {
            res.add(i);
            i = pre[i];
        }
        Collections.reverse(res);
        System.out.println(res);
    }
}
