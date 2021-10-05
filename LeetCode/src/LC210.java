import java.util.*;

public class LC210 {
    public static void main(String[] args) {
//        findOrder(2, new int[][]{{1,0}});
        findOrder2(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}); //[0,1,2,3] or [0,2,1,3]
    }
    public static void dfs2(int[] inDegree, Map<Integer, List<Integer>>m, boolean[] visited, List<Integer> res) {
        if (res.size() == inDegree.length) {
            System.out.println(res);
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && inDegree[i] == 0) {

                for (int next : m.getOrDefault(i, new ArrayList<>())) {
                    inDegree[next]--;
                }
                visited[i] = true;
                res.add(i);
                dfs2(inDegree, m, visited, res);
                visited[i] = false;
                res.remove(res.size() - 1);
                for (int next : m.getOrDefault(i, new ArrayList<>())) {
                    inDegree[next]++;
                }
            }
        }
    }
    public static void dfs(int courseNo, Map<Integer, List<Integer>>m, boolean[] visited, Stack<Integer> res) {
        visited[courseNo] = true;
        for (int course : m.getOrDefault(courseNo, new ArrayList<>())) {
            if (!visited[course]) {
                dfs(course, m, visited, res);
            }
        }
        res.push(courseNo);
    }
    public static void findOrder2(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> preCourses = new HashMap<>();
        int[] in = new int[numCourses];
        for (int[] course : prerequisites) {
            preCourses.computeIfAbsent(course[1], k -> new ArrayList<>()).add(course[0]);
            in[course[0]]++;
        }
        List<Integer> res = new ArrayList<>();
//        for (int i = 0; i < in.length; i++) {
//            if (in[i] == 0) {
//                res.add(i);
//            }
//        }
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[numCourses];
        dfs2(in, preCourses, visited, res);
//        for (int i = 0; i < numCourses; i++) {
//            if (!visited[i]) {
//                dfs(i, preCourses, visited, st);
//            }
//        }
//        List<Integer> all = new ArrayList<>(st);
//        Collections.reverse(all);
//        System.out.println(all);
    }
    public static void findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> preCourses = new HashMap<>();
        int[] in = new int[numCourses];
        for (int[] course : prerequisites) {
            preCourses.computeIfAbsent(course[1], k -> new ArrayList<>()).add(course[0]);
            in[course[0]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                q.offer(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int idx = q.poll();
            res.add(idx);
            for (Integer next : preCourses.getOrDefault(idx, new ArrayList<>())) {
                if (--in[next] == 0) {
                    q.offer(next);
                }
            }
        }
        System.out.println(res);
    }
}
