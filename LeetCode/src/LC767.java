import java.util.*;

public class LC767 {
    public static void main(String[] args) {
        reorganizeString("bba");
//        subset(new int[]{1, 2, 3});
//        subset(new int[]{1, 2, 2});
        permute(new int[]{1, 2, 2});
    }
    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    public static void dfs2(int idx, int[] arr) {
        if (idx >= arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = idx; i < arr.length; i++) {
            int j = i - 1;
            while (j >= idx && arr[j] != arr[i]) --j;
            if (j != idx - 1) continue;
            swap(arr, idx, i);
            dfs2(idx + 1, arr);
            swap(arr, idx, i);
        }
    }
    public static void permute(int[] arr) {
        dfs2(0, arr);
    }
    public static void dfs(int[] arr, int idx, List<Integer> res, List<List<Integer>> all) {
        all.add(new ArrayList<>(res));
        for (int i = idx; i < arr.length; i++) {
            res.add(arr[i]);
            dfs(arr, i + 1, res, all);
            res.remove(res.size() - 1);
            while (i + 1 < arr.length && arr[i] == arr[i + 1]) i++;
        }
    }
    public static void subset(int[] arr) {
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> all = new ArrayList<>();
        dfs(arr, 0, res, all);
        System.out.println(all);
    }
    static class Node {
        char c;
        int cnt;
        public Node(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }
    }
    public static void reorganizeString(String s) {
        String res = "";
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> b.cnt - a.cnt);
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            q.offer(new Node(entry.getKey(), entry.getValue()));
        }
        while (!q.isEmpty()) {
            Node cur =  q.poll();
            if (res.length() > 0 && cur.c == res.charAt(res.length() - 1)) {
                res = "";
                break;
            }
            res += cur.c;
            if (--cur.cnt > 0) {
                q.offer(cur);
            }
        }
        System.out.println(res);
    }
}
