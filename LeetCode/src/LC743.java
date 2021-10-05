import java.util.HashMap;
import java.util.PriorityQueue;

public class LC743 {
    public static void main(String ...args) {
        System.out.println(networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}}, 4, 2));
    }
    public static int networkDelayTime(int[][] times, int N, int K) {
        HashMap<Integer, HashMap<Integer, Integer>> g = new HashMap<>();
        // times中的数组 a, a[0]代表源，a[1]代表目标，a[2]代表权值，单向的
        for (int[] t : times) {
            g.putIfAbsent(t[0], new HashMap<>());
            g.get(t[0]).put(t[1], t[2]);
        }

        // 距离, 目标
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[] { 0, K });
        boolean[] visited = new boolean[N + 1];
        int res = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDistance = cur[0];
            int curNode = cur[1];
            if (visited[curNode] == true) {
                continue;
            }
            visited[curNode] = true;
            res = curDistance;
            N--;
            if (N == 0) {
                break;
            }
            if (g.containsKey(curNode)) {
                for (int next : g.get(curNode).keySet()) {
                    pq.offer(new int[] { curDistance + g.get(curNode).get(next), next });
                }
            }
        }
        return N == 0 ? res : -1;
    }
}
