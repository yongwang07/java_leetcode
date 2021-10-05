package facebook;

import java.util.*;

public class LC329 {
    public static void main(String[] args) {
        //LC723
        longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}});
        longestIncreasingPath(new int[][]{{3,4,5},{3,2,6},{2,2,1}});
        accountsMerge(new String[][]{{"John", "johnsmith@mail.com", "john00@mail.com"}, {"John", "johnnybravo@mail.com"},
                {"John", "johnsmith@mail.com", "john_newyork@mail.com"}, {"Mary", "mary@mail.com"}});
        simplifyPath("/a/./b/../../c/");
        simplifyPath("/../");
        simplifyPath("/home//foo/");
        simplifyPath("/a/./b/../../c/");
        rearrangeString("aabbcc", 3);
        rearrangeString("aaadbbcc", 2);
        rearrangeString("aaabc", 3);
        restoreIpAddresses("25525511135");
        maxEnvelopes(new int[][]{{5,4},{6,4},{6,7},{2,3}});
    }
    private static int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (arr1, arr2) -> arr1[0] == arr2[0] ? arr2[1] - arr1[1] : arr1[0] - arr2[0]);
        int dp[] = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if(index < 0) index = -(index + 1);
            dp[index] = envelope[1];
            if(index == len) len++;
        }
        System.out.println("len=" + len);
        return len;
    }
    private static void helper(String s, List<String> res) {
        if (s.length() == 0) {
            if (res.size() == 4) {
                System.out.println(String.join(".", res));
            }
        } else {
            for (int i = 0; i < Math.min(s.length(), 3); i++) {
                String part = s.substring(0, i + 1);
                int ip = Integer.parseInt(part);
                if (ip <= 255 && ip >= 0) {
                    res.add(part);
                    helper(s.substring(i + 1), res);
                    res.remove(res.size() - 1);
                }
            }
        }
    }
    private static void restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        helper(s, res);
    }
    private static void rearrangeString(String str, int k) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0] == a[0] ? a[1] - b[1] : b[0] - a[0]);
        Map<Character, Integer> m = new HashMap<>();
        for (char c : str.toCharArray()) {
            m.put(c, m.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> item : m.entrySet()) {
            q.offer(new int[]{item.getValue(), item.getKey() - 'a'});
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int cycle = Math.min(len, k);
            List<int[]> t = new ArrayList<>();
            for (int i = 0; i < cycle; i++) {
                if (q.isEmpty()) {
                    System.out.println("\"\"");
                    return;
                }
                int[] cur = q.poll();
                sb.append((char)(cur[1] + 'a'));
                if (--cur[0] > 0) t.add(cur);
                --len;
            }
            for (int[] next : t) {
                q.offer(next);
            }
        }
        while (!q.isEmpty()) {
            sb.append((char)(q.poll()[1] + 'a'));
        }
        System.out.println(sb);
    }
    private static void simplifyPath(String path) {
        Stack<String> st = new Stack<>();
        for (String dir : path.split("/")) {
            if (dir.trim().length() == 0 || dir.equals(".")) continue;
            if (dir.equals("..")) {
                if (!st.isEmpty()) st.pop();
            } else {
                st.push(dir);
            }
        }
        String res = "/" + String.join("/", st);
        System.out.println(res);
    }
    private static String find(String s, Map<String, String> root) {
        return root.get(s) == s ? s : find(root.get(s), root);
    }
    private static void accountsMerge(String[][] accounts) {
        Map<String, String> emailOwner = new HashMap<>();
        Map<String, String> root = new HashMap<>();
        Map<String, Set<String>> m = new HashMap<>();
        for (String[] account : accounts) {
            for (int i = 1; i < account.length; i++) {
                root.put(account[i], account[i]);
                emailOwner.put(account[i], account[0]);
            }
        }
        for (String[] account : accounts) {
            String p = "";
            for (int i = 1; i < account.length; i++) {
                String q = find(account[i], root);
                if (p.equals("")) p = q;
                root.put(q, p);
                m.computeIfAbsent(p, k -> new HashSet<>()).add(account[i]);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, Set<String>> a : m.entrySet()) {
            List<String> r = new ArrayList<>(a.getValue());
            r.add(0, emailOwner.get(a.getKey()));
            res.add(r);
        }
        System.out.println(res);
    }
    private static int helper(int i, int j, int[][] matrix, int[][] dp, int[][] pre) {
        if (dp[i][j] != 0) return dp[i][j];
        int max = 1, parent = -1, m = matrix.length, n = matrix[0].length;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : dirs) {
            int x = dir[0] + i, y = dir[1] + j;
            if (x < 0 || y < 0 || x >= m || y >= n) continue;
            if (matrix[x][y] <= matrix[i][j]) continue;
            int len = 1 + helper(x, y, matrix, dp, pre);
            if (len > max) {
                parent = x * n + y;
                max = len;
            }
        }
        pre[i][j] = parent;
        dp[i][j] = max;
        return max;
    }
    private static void longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, res = 1;
        int[][] dp = new int[m][n];
        int[][] pre = new int[m][n];
        int maxPoint = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int len = helper(i, j, matrix, dp, pre);
                if (len > res) {
                    res = len;
                    maxPoint = i * m + j;
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        int cur = maxPoint;
        while (cur != -1) {
            path.add(matrix[cur/m][cur%m]);
            cur = pre[cur/m][cur%m];
        }
        System.out.println(path);
    }
}
