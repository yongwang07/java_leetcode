package facebook;

import java.util.*;
import java.util.stream.Collectors;

public class Karat3 {
//    https://leetcode.com/discuss/interview-question/1063081/Indeed-or-Karat-(Video-Screen)-or-Find-Rectangles
//    https://www.1point3acres.com/bbs/thread-687267-2-1.html
    public static void main(String[] args) {
        String[] c = new String[] {"3123122444","234111110", "8321125440", "99911063"};
        String[] clicks = new String[]{
                "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
        "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
                "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
                "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
                "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
                "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens"};
        String[] ips = new String[]{"2339985511,122.121.0.155",
                "234111110,122.121.0.1",
                "3123122444,92.130.6.145",
                "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
                "8321125440,82.1.106.8",
                "99911063,92.130.6.144"};
        adCount(c, clicks, ips);
        findShapes(new int[][]{
                {1, 0, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 1, 1},
                {0, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1},
                {1, 0, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 1, 1},
                {1, 1, 1, 0, 0, 1, 1},
                {0, 1, 0, 1, 1, 1, 0}});
        friendCycle(new String[]{"1,Bill,Engineer", "2,Joe,HR", "3,Sally,Engineer",
                        "4,Richard,Business", "6,Tom,Engineer"}, new String[]{"1,2", "1,3", "3,4"});
    }
    private static void circle(Integer id, Map<Integer, List<Integer>> friends, Set<Integer> all) {
        all.add(id);
        for (Integer it : friends.get(id)) {
            if (!all.contains(it)) circle(it, friends, all);
        }
    }
    public static void friendCycle(String[] employees, String[] friendships) {
        Map<Integer, List<Integer>> res = new HashMap<>();
        Map<Integer, String> dep = new HashMap<>();
        Map<String, Integer> depCnt = new HashMap<>();
        for (String employee : employees) {
            String[] split = employee.split(",");
            int id = Integer.parseInt(split[0]);
            res.put(id, new ArrayList<>());
            dep.put(id, split[2]);
            depCnt.put(split[2], depCnt.getOrDefault(split[2], 0) + 1);
        }
        for (String f : friendships) {
            final String[] split = f.split(",");
            int a = Integer.parseInt(split[0]);
            int b = Integer.parseInt(split[1]);
            res.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            res.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        System.out.println(res);
        Map<String, Set<Integer>> st = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> f : res.entrySet()) {
            String depart = dep.get(f.getKey());
            for (int fId : f.getValue()) {
                if (!dep.get(fId).equals(depart)) {
                    st.computeIfAbsent(depart, k -> new HashSet<>()).add(fId);
                }
            }
        }
        for (Map.Entry<String, Set<Integer>> f : st.entrySet()) {
            String s = String.format("%s: %d of %d", f.getKey(), f.getValue().size(), depCnt.get(f.getKey()));
            System.out.println(s);
        }
        res.computeIfAbsent(6, k -> new ArrayList<>()).add(1);
        res.computeIfAbsent(1, k -> new ArrayList<>()).add(6);
        System.out.println("+++" + res);
        Set<Integer> all = new HashSet<>();
        circle(1, res, all);
        System.out.println(all.size() == employees.length);
    }
    private static void dfs(int x, int y, int[][] image, boolean[][] visited, List<int[]> path, int m, int n) {
        if (x < 0 || y < 0 || x >= m || y >= n) return;
        if (image[x][y] == 1 || visited[x][y]) return;
        visited[x][y] = true;
        path.add(new int[]{x,y});
        dfs(x + 1, y, image,  visited, path, m, n);
        dfs(x - 1, y, image, visited, path, m, n);
        dfs(x, y - 1, image, visited, path, m, n);
        dfs(x, y + 1, image, visited, path, m, n);
    }
    public static void findShapes(int[][] image) {
        int m = image.length, n = image[0].length;
        boolean visited[][] = new boolean[m][n];
        List<int[][]> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || image[i][j] == 1) continue;
                List<int[]> w = new ArrayList<>();
                System.out.println(i + "," + j);
                dfs(i, j, image, visited, w, m, n);
//                System.out.println(w);
            }
        }
    }
    public static void adCount(String[] completed, String[] clicks, String[] ips) {
        final Set<String> c = Arrays.stream(completed).collect(Collectors.toSet());
        final Set<String> ipCompleted = Arrays.stream(ips)
                .map(line -> line.split(","))
                .filter(a -> c.contains(a[0]))
                .map(a -> a[0])
                .collect(Collectors.toSet());
        Map<String, int[]> m = new HashMap<>();
        for (String clickStr : clicks) {
            String[] click = clickStr.split(",");
            String ad = click[2];
            String ip = click[0];
            int[] old = m.getOrDefault(ad, new int[2]);
            old[0]++;
            if (ipCompleted.contains(ip)) old[1]++;
            m.put(ad, old);
        }
        for (Map.Entry<String, int[]> e : m.entrySet()) {
            String ad = e.getKey();
            int[] cnt = e.getValue();
            System.out.println(String.format("%d of %d %s", cnt[1], cnt[0], ad));
        }
    }
}
