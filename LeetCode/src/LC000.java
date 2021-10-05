import java.util.*;
import java.util.stream.Collectors;

public class LC000 {
    public static void main(String args[]) {
//        performEditorActions(new String[][]{{"INSERT", "a"}, {"INSERT", "b"}});
//        performEditorActions(new String[][]{{"INSERT", "a"}, {"INSERT", "b"}, {"UNDO"}});
//        performEditorActions(new String[][]{{"INSERT", "a"}, {"INSERT", "b"}, {"UNDO"}, {"REDO"}});
//        performEditorActions(new String[][]{{"INSERT", "a"}, {"INSERT", "b"}, {"UNDO"}, {"REDO"}, {"REDO"}});
//        loadBalance(new int[]{2, 4, 5, 3, 3, 9, 2, 2, 2});
//        maxProfit(new int[]{4, 6}, 4);
//        maxProfit(new int[]{3, 5}, 6);
//        maxProfit(new int[]{2,8,4,10,6}, 20); //110
//        maxProfit(new int[]{1000000000}, 1000000000);
//        critica(new int[][]{{0, 1}, {1, 2}, {2, 0}, {1, 3}}, 4);
        canFormString("catsanddog", new String[]{"cat", "cats", "and", "sand", "dog"});
        canFormString("catsandog", new String[]{"cats", "dog", "sand", "and", "cat"});
    }
    public static String performEditorActions(String[][] actions) {
        Stack<String> history = new Stack<>();
        Stack<String> textStack = new Stack<>();
        String curStr = "";
        textStack.push(curStr);
        for (String[] action: actions) {
            String command = action[0];
            if (command.equals("INSERT")) {
                curStr += action[1];
                textStack.push(curStr);
            } else if (command.equals("UNDO")) {
                if (textStack.size() > 0) {
                    history.push(textStack.pop());
                    curStr = textStack.peek();
                }
            } else {
                if (history.size() > 0) {
                    textStack.push(history.pop());
                    curStr = textStack.peek();
                }
            }
        }
        System.out.println("res=" + curStr);
        return curStr;
    }
    public static boolean loadBalance(int[] arr) {
        int sum = 0;
        int len = arr.length;
        if(len < 5){
            return false;
        }
        for(int i =0;i<len;i++) {
            sum += arr[i];
        }
        int prefixSum = arr[0], suffixSum = arr[len-1];
        int left = 1, right = len - 2;
        boolean flag = false;
        while(left < right - 1) {
            int midSum = sum - prefixSum - suffixSum - arr[left] - arr[right];
            if(prefixSum == midSum && suffixSum == midSum) {
                flag = true;
                break;
            } else {
                if(prefixSum < suffixSum) {
                    prefixSum += arr[left];
                    left++;
                } else if(prefixSum > suffixSum) {
                    suffixSum += arr[right];
                    right--;
                } else {
                    prefixSum += arr[left];
                    suffixSum += arr[right];
                    left++;
                    right--;
                }
            }
        }
        if (flag) {
            System.out.println(Arrays.toString(Arrays.copyOf(arr, left)) + "," +
                    Arrays.toString(Arrays.copyOfRange(arr, right + 1, arr.length)));
        }
        return flag;
    }

    public static long calcPrice(int from, int to, int group) {
        long gap = from - to;
        long res = ((gap * to + (gap + 1) * gap / 2) * group);
        return res;
    }
    public static long move(int from, int to, int group, int order) {
        int total = group * (from - to);
        if (total > order) {
            int nTo = from - order / group;
            int nR = order % group;
            return calcPrice(from, nTo - 1, nR) + calcPrice(from, nTo, group - nR);
        } else {
            return calcPrice(from, to, group);
        }
    }

    public static void maxProfit(int[] arr, int order) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i : arr) {
            m.put(i, m.getOrDefault(i, 0) + 1);
        }
        List<int[]> list = m.entrySet()
                .stream()
                .map(entry -> new int[]{entry.getKey(), entry.getValue()})
                .sorted((a, b) -> b[0] - a[0])
                .collect(Collectors.toList());
        long res = 0;
        int cur = 0;
        int preGroup = 0;
        while (order > 0 && cur < list.size()) {
            int group = list.get(cur)[1] + preGroup;
            int value = list.get(cur)[0];
            int next = list.size() > cur + 1 ? list.get(cur + 1)[0] : 0;
            res += move(value, next, group, order);
            res %= Math.pow(10, 9) + 7;
            order -= group * (value - next);
            cur++;
            preGroup = group;
        }
        System.out.println(res);
    }

    public static int findRoot(int i, int[] root) {
        while (root[i] != -1) {
            i = root[i];
        }
        return i;
    }
    public static void critica(int[][] connections, int N) {
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            int[] root = new int[N];
            Arrays.fill(root, -1);
            for (int j = 0; j < connections.length; j++) {
                if (j == i) continue;
                int[] conn = connections[j];
                int a = findRoot(conn[0], root);
                int b = findRoot(conn[1], root);
                if (a != b) {
                    root[a] = b;
                }
            }
            if (findRoot(from, root) != findRoot(to, root)) {
                System.out.println(from + "=>" + to);
            }
        }
    }
    public static void dfs(int idx, Map<Integer, List<Integer>> dp, String s, List<String> temp, List<List<String>> words) {
        List<Integer> pre = dp.get(idx);
        if (pre == null) {
            words.add(new ArrayList<>(temp));
            return;
        }
        for (int preIdx : pre) {
            String one = s.substring(preIdx, idx + 1);
            temp.add(one);
            dfs(preIdx - 1, dp, s, temp, words);
            temp.remove(temp.size() - 1);
        }
    }
    public static void canFormString(String input, String[] strArr) {
        System.out.println(input + ":" + Arrays.toString(strArr));
        Set<String> dict = new HashSet<>();
        for (String word : strArr) {
            dict.add(word);
        }
        Map<Integer, List<Integer>> dp = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            for (int j = i; j >= 0; j--) {
                boolean pre = j >= 1 ? dp.get(j - 1) != null : true;
                if (pre && dict.contains(input.substring(j, i + 1))) {
                    dp.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }
        boolean canSplit = dp.get(input.length() - 1) != null;
        System.out.println(canSplit + "=>" + dp);
        if (canSplit) {
            List<String> temp = new ArrayList<>();
            List<List<String>> words = new ArrayList<>();
            dfs(input.length() - 1, dp, input, temp, words);
            System.out.println(words);
        }
    }

    public static void canFormString2(String input, String[] strArr) {
        System.out.println(input + ":" + Arrays.toString(strArr));
        Set<String> dict = new HashSet<>();
        for (String word : strArr) {
            dict.add(word);
        }
        boolean[] dp = new boolean[input.length()];
        for (int i = 0; i < input.length(); i++) {
            for (int j = i; j >= 0; j--) {
                boolean pre = j >= 1 ? dp[j - 1] : true;
                if (pre && dict.contains(input.substring(j, i + 1))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        System.out.println(dp[dp.length - 1]);
    }
}
