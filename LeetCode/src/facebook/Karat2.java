package facebook;

import java.util.*;

public class Karat2 {
//    https://juejin.cn/post/6844904085913600008
//    What composition and inheritance?
//    When to choose composition and when to choose inheritance?
//    What is dependency injection?
//    3如果向Collectionframework(例如TreeMap)加入一个新的class需要加上哪些文档？
    public static void main(String[] args) {
        findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7});
        numRescueBoats(new int[]{1,2}, 3);
        numRescueBoats(new int[]{3,2,2,1}, 3);
        numRescueBoats(new int[]{3,5,3,4}, 5);
        subdomainVisits(new String[]{"9001 discuss.leetcode.com"});
        subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"});
        findParent(new int[][]{{1, 3}, {2, 3}, {5, 2}, {5, 4}, {7, 4}, {7, 4}, {7, 6}});
    }

    public static void dfs(int cur, Map<Integer, List<Integer>> m, Set<Integer> parents) {
//        if (cur == 0) return;
        parents.add(cur);
        for (Integer p : m.get(cur)) {
            if (!parents.contains(p)) {
                dfs(p, m, parents);
            }
        }
    }
    public static void dfs2(int cur, Map<Integer, List<Integer>> m, Set<Integer> parents) {
        if (m.get(cur).size() == 0) {
            parents.add(cur);
            return;
        }
        for (Integer p : m.get(cur)) {
            if (!parents.contains(p)) {
                dfs2(p, m, parents);
            }
        }
    }
    public static void findParent(int[][] relation) {
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int[] r : relation) {
            m.computeIfAbsent(r[1], k -> new ArrayList<>()).add(r[0]);
            m.put(r[0], new ArrayList<>());
        }
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry :  m.entrySet()) {
            if (entry.getValue().size() == 0 || entry.getValue().size() == 1) {
                res.add(entry.getKey());
            }
        }
        Set<Integer> parents = new HashSet<>();
        dfs(3, m, parents);
        Set<Integer> p2 = new LinkedHashSet<>();
        dfs(3, m, p2);
        Set<Integer> w = new HashSet<>();
        dfs2(3, m, w);
        System.out.println((m.get(2).size() == 0) + "????" + w);
        boolean common = false;
        final Iterator<Integer> iterator = p2.iterator();
        while (iterator.hasNext()) {
            System.out.println("?" + iterator.next());
        }
//        for (Integer p : p2) {
//            if (parents.contains(p)) {
//                common = true;
//                break;
//            }
//        }
        System.out.println("common=" + common);
        System.out.println(res);
    }

    public static void subdomainVisits(String[] cpdomains) {
        Map<String, Integer> m = new HashMap<>();
        for (String domain : cpdomains) {
            String[] cntAndDomain = domain.split(" ");
            String d = cntAndDomain[1];
            int cnt = Integer.parseInt(cntAndDomain[0]);
            m.put(d, m.getOrDefault(d, 0) + cnt);
            for (int start = d.indexOf('.'); start >= 0; start = d.indexOf('.', start)) {
                String sub = d.substring(start + 1);
                m.put(sub, m.getOrDefault(sub, 0) + cnt);
                start = start + 1;
            }
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : m.entrySet()) {
            res.add(entry.getValue() + " " + entry.getKey());
        }
        System.out.println(res);
    }
    public static void numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0, right = people.length - 1, res = 0;
        while (left < right) {
            if (people[right] + people[left] > limit) {
                res++;
                right--;
            } else if (people[right] + people[left] <= limit) {
                res++;
                right--;
                left++;
            }
        }
        if (right == left) res++;
        System.out.println(res);
    }
    public static void findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];
        int res = 0, maxi = -1;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > res) {
                        res = dp[i][j];
                        maxi = j;
                    }
                }
            }
        }
        int[] common = Arrays.copyOfRange(B, maxi - res, maxi);
        System.out.println(res + "," + Arrays.toString(common));
    }
}
