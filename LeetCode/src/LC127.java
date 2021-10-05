import java.util.*;

public class LC127 {
    public static void main(String[] args) {
        ladderLength3("hit", "cog", new String[]{"hot","dot","dog","lot","log","cog"});
//       ladderLength("hit", "cog", new String[]{"hot","dot","dog","lot","log","cog"});
//        getAllShortestPaths(new int[][]{{1, 2, 1}, {2, 3, 1}, {3, 4, 1}, {4, 5, 1}, {5, 1, 3}, {1, 3, 2}, {5, 3, 1}},
//        1, 5, 5);
    }

    static class PNode {
        PNode pre;
        String word;
        int dis = 0;
        public PNode(PNode pre, String word, int dis) {
            this.pre = pre;
            this.word = word;
            this.dis = dis;
        }
    }
    public static void ladderLength3(String beginWord, String endWord, String[] wordList) {
        Set<String> dict = new HashSet<>(Arrays.asList(wordList));
        Queue<PNode> q = new PriorityQueue<>((a, b) -> a.dis - b.dis);
        q.offer(new PNode(null, beginWord, 0));
        int dis = 0, minDis = 1000;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                PNode cur = q.poll();
                if (dis > minDis) break;
                if (cur.word.equals(endWord)) {
                    minDis = dis;
                    List<String> res = new ArrayList<>();
                    res.add(cur.word);
                    PNode pre = cur.pre;
                    while (pre != null) {
                        res.add(pre.word);
                        pre = pre.pre;
                    }
                    Collections.reverse(res);
                    System.out.println(res);
                    continue;
                }
                for (int j = 0; j < cur.word.length(); j++) {
                    for (char k = 'a'; k < 'z'; k++) {
                        if (cur.word.charAt(j) == k) continue;
                        String newWord = cur.word.substring(0, j) + k + cur.word.substring(j + 1);
                        if (!dict.contains(newWord)) continue;
                        q.offer(new PNode(cur, newWord, dis + 1));
                    }
                }
            }
            dis++;
        }
    }
    public static void dfs(String str, Map<String, List<String>> preMap, List<String> temp, List<List<String>> res) {
        if (preMap.get(str) == null) {
            List<String> a = new ArrayList<>(temp);
            Collections.reverse(a);
            res.add(a);
            return;
        }
        for (String pre : preMap.get(str)) {
            temp.add(pre);
            dfs(pre, preMap, temp, res);
            temp.remove(temp.size() - 1);
        }
    }
    public static void ladderLength(String beginWord, String endWord, String[] wordList) {
        System.out.println(beginWord + ":" + endWord + ":" + Arrays.toString(wordList));
        Set<String> dict = new HashSet<>(Arrays.asList(wordList));
        dict.add(endWord);
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        visited.add(beginWord);
        int level = 0;
        Map<String, List<String>> preMap = new HashMap<>();
        Map<String, Integer> preLenMap = new HashMap<>();
        preLenMap.put(beginWord, null);
        preLenMap.put(beginWord, 0);
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                String cur = q.poll();
                visited.add(cur);
                if (cur.equals(endWord)) {
                    continue;
                }
                for (int j = 0; j < cur.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == cur.charAt(j)) continue;
                        String newWord = cur.substring(0, j) + c + cur.substring(j + 1);
                        if (!dict.contains(newWord) || visited.contains(newWord)) continue;
                        int len = preLenMap.getOrDefault(newWord, Integer.MAX_VALUE);
                        if (len < level) continue;;
                        preMap.computeIfAbsent(newWord, k-> new ArrayList<>()).add(cur);
                        preLenMap.put(newWord, level);
                        q.offer(newWord);
                    }
                }
            }
            level++;
        }
        System.out.println(preMap + "===>" + level);
        if (preMap.get(endWord) != null) {
            List<String> temp = new ArrayList<>();
            temp.add(endWord);
            List<List<String>> res = new ArrayList<>();
            dfs(endWord, preMap, temp, res);
            System.out.println(res);
        }
    }
    public static void ladderLength2(String beginWord, String endWord, String[] wordList) {
        System.out.println(beginWord + ":" + endWord + ":" + Arrays.toString(wordList));
        Set<String> dict = new HashSet<>(Arrays.asList(wordList));
        dict.add(endWord);
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int level = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                String cur = q.poll();
                if (cur.equals(endWord)) {
                    System.out.println(level);
                    return;
                }
                for (int j = 0; j < cur.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == cur.charAt(j)) continue;
                        String newWord = cur.substring(0, j) + c + cur.substring(j + 1);
                        if (!dict.contains(newWord)) continue;
                        q.offer(newWord);
                        dict.remove(newWord);
                    }
                }
            }
            level++;
        }
        System.out.println("no found");
    }

    public static class Node {
        private int city;
        private int pathId;
        private int cost;
        public Node (int city, int pathId, int cost) {
            this.city = city;
            this.pathId = pathId;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "city=" + city +
                    ", pathId=" + pathId +
                    ", cost=" + cost +
                    '}';
        }
    }
    public static void getAllShortestPaths(int[][] routes, int S, int D, int N) {
        Map<Integer, List<Node>> path = new HashMap<>();
        int pathId = 1;
        for (int[] route : routes) {
            path.computeIfAbsent(route[0], k -> new ArrayList<>()).add(new Node(route[1], pathId, route[2]));
            path.computeIfAbsent(route[1], k -> new ArrayList<>()).add(new Node(route[0], pathId, route[2]));
            pathId++;
        }
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        Node first = new Node(S, 0, 0);
        q.offer(first);
        Map<Integer, Integer> pathCost = new HashMap<>();
        Map<Integer, List<Integer>> preCities = new HashMap<>();
        pathCost.put(S, 0);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.city == D) {
                System.out.println("got:" + pathCost.get(cur.city));
            }
            for (Node node : path.get(cur.city)) {
                int curCost = pathCost.get(cur.city);
                int cost = pathCost.getOrDefault(node.city, Integer.MAX_VALUE);
                if (cost < curCost + node.cost) {
                    continue;
                };
                q.offer(node);
                preCities.computeIfAbsent(node.city, k -> new ArrayList<>()).add(cur.city);
                pathCost.put(node.city, curCost + node.cost);
            }
        }
        System.out.println(pathCost);
        System.out.println(preCities);
    }
}
