package facebook;


import java.util.*;

public class Karat4 {
//    https://leetcode.com/discuss/interview-question/992306/Indeed-or-Karat-(Phone-Screen)-or-Find-Embedded-Words-I-II
    public static void main(String[] args) {
        findPairs(new String[][]{{"58", "Software Design"}, {"58", "Linear Algebra"},
                {"94", "Art History"}, {"94", "Operating Systems"}, {"17", "Software Design"},
                {"58", "Mechanics"}, {"58", "Economics"}, {"17", "Linear Algebra"},
                {"17", "Political Science"}, {"94", "Economics"}, {"25", "Economics"}});
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        findLeaves(root);
        merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}); // [[1,6],[8,10],[15,18]]
        findRectangle(new int[][]{{1,1,1,1,1}, {1,1,0,0,1}, {1,1,0,0,1}, {1,1,1,1,1}});
        findRectangle3(new int[][]{
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 1, 0},
                {0, 1, 0, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0}});
    }
    private static void dfs(int x, int y, int[][] matrix, boolean[][] visited, int[] p) {
        if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) return;
        if (visited[x][y] || matrix[x][y] == 0) return;
        visited[x][y] = true;
        p[2] = Math.max(p[2], x);
        p[3] = Math.max(p[3], y);
        dfs(x + 1, y, matrix, visited, p);
        dfs(x - 1, y, matrix, visited, p);
        dfs(x, y - 1, matrix, visited, p);
        dfs(x, y + 1, matrix, visited, p);
    }
    public static void findRectangle3(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean visited[][] = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || matrix[i][j] == 0) continue;
                int[] p = new int[4];
                p[0] = i;
                p[1] = j;
                dfs(i, j, matrix, visited, p);
                System.out.println(String.format("(%d, %d, %d, %d)", p[0], p[1], p[2], p[3]));
            }
        }
    }
    public static void findRectangle2(int[][] matrix) {
        int res = 0, m = matrix.length, n = matrix[0].length;
        int[][] hSum = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) continue;
                if (j > 0) hSum[i][j] = hSum[i][j - 1] + 1;
                else hSum[i][0] = 1;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (hSum[i][j] == 0) continue;
                int mn = hSum[i][j];
                res = Math.max(res, mn);
                for (int k = i - 1; k >= 0 && hSum[k][j] != 0; k--) {
                    mn = Math.min(mn, hSum[k][j]);
                    res = Math.max(res, mn * (i - k + 1));
                }
            }
        }
        System.out.println(res);
    }
    public static void findRectangle(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int firstX = -1, firstY = -1, lastX = -1, lastY = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (firstX == -1) {
                        firstX = i;
                        firstY = j;
                    }
                    lastX = i;
                    lastY = j;
                }
            }
        }
        System.out.println(String.format("(%d,%d)=(%d,%d)", firstX, firstY, lastX, lastY));
    }
    public static void merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        res.add(new int[]{0, 0});
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] < pre[1]) {
                pre[0] = Math.min(cur[0], pre[0]);
                pre[1] = Math.max(cur[1], pre[1]);
            } else {
                System.out.println(res.get(res.size() - 1)[1] + "-" + pre[0]);
                res.add(pre);
                pre = cur;
            }
        }
        System.out.println(res.get(res.size() - 1)[1] + "-" + pre[0]);
        res.add(pre);
    }
    static class TreeNode {
        int value;
        TreeNode left, right;
        public TreeNode(int value) {
            this.value = value;
        }
    }
    private static int dfs(TreeNode node, List<List<Integer>> res) {
        if (node == null) return 0;
        int left = dfs(node.left, res);
        int right = dfs(node.right, res);
        int level = Math.max(left, right);
        if (level < res.size()) res.get(level).add(node.value);
        else {
            res.add(new ArrayList<>());
            res.get(res.size() - 1).add(node.value);
        }
        return level + 1;
    }
    public static void findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res);
        System.out.println(res);
    }
    public static void findPairs(String[][] pair) {
        Map<String, Set<String>> courses = new HashMap<>();
        Map<String, List<String>> commonCourses = new HashMap<>();
        Set<String> all = new HashSet<>();
        for (String[] course : pair) {
            all.add(course[0]);
            Set<String> ids = courses.getOrDefault(course[1], new HashSet<>());
            if (!ids.contains(course[0])) {
                for (String id : ids) {
                    String key = id.compareTo(course[0]) > 0 ? course[0] + "-" + id : id + "-" + course[0];
                    commonCourses.computeIfAbsent(key, k -> new ArrayList<>()).add(course[1]);
                }
            }
            ids.add(course[0]);
            courses.put(course[1], ids);
        }
        while (all.size() > 0) {
            String next = all.iterator().next();
            all.remove(next);
            for (String id : all) {
                String key = id.compareTo(next) > 0 ? next + "-" + id : id + "-" + next;
                List<String> cs = commonCourses.getOrDefault(key, new ArrayList<>());
                System.out.println(String.format("[%s,%s]:%s", key.split("-")[0], key.split("-")[1], cs));
            }
        }
    }
}
