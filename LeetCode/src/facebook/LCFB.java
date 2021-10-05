package facebook;

import java.util.*;

public class LCFB {
    public static void main(String[] args) {
        getArtisticPhotographCount(8, ".PBAAP.B", 1, 3); // 3
        getArtisticPhotographCount(5, "APABA", 2, 3); // 0
        getArtisticPhotographCount(5, "APABA", 1, 2); // 1
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.right = new Node(7);
        connect(root);
        level(root.left.left);
        Map<Integer, Map<Integer, Integer>> matrix = new HashMap<>();
        matrix.put(0, new HashMap<>());
        matrix.get(0).put(0, 1);
        matrix.get(0).put(3, 1);
        matrix.put(1, new HashMap<>());
        matrix.get(1).put(1, 1);
        System.out.println(matrix);
        int[][] m = new int[][]{{2, 0}, {2, 2}, {0, 2}, {2, 2}, {0, 0}};
        Map<Integer, Map<Integer, Integer>> res = new HashMap<>();
        for (int row : matrix.keySet()) {
            for (int col : matrix.get(row).keySet()) {
                if (m[col][row] != 0) {
                    res.putIfAbsent(row, new HashMap<>());
                    for (int j = 0; j < m[0].length; j++) {
                        int a = matrix.get(row).get(col) * m[col][j];
                        res.get(row).put(j, res.get(row).getOrDefault(j, 0) + a);
                    }
                }
            }
        }
        System.out.println(res);
        getMaximumEatenDishCount(6, new int[]{1, 2, 3, 3, 2, 1}, 1);
        getMaximumEatenDishCount(6, new int[]{1, 2, 3, 3, 2, 1}, 2);
        getMaximumEatenDishCount(7, new int[]{1, 2, 1, 2, 1, 2, 1}, 2);
        getMinCodeEntryTime(3 , 3, new int[]{1, 2, 3});
        getMinCodeEntryTime(10, 4, new int[] {9, 4, 4, 8});
        getMinProblemCount(6, new int[]{1, 2, 3, 4, 5, 6});
        getMinProblemCount(4, new int[]{4, 3, 3, 4});
        getMinProblemCount(4, new int[]{2, 4, 6, 8});
        getMinimumDeflatedDiscCount(5, new int[]{2, 5, 3, 6, 5});
        getMinimumDeflatedDiscCount(3, new int[]{100, 100, 100});
        getMinimumDeflatedDiscCount(4, new int[]{6, 5, 4, 3});
        getMinimumDeflatedDiscCount(2, new int[]{4, 4});
        getUniformIntegerCountInInterval(999999999999l, 999999999999l);
//        getUniformIntegerCountInInterval(82, 90);
    }
    public static int getUniformIntegerCountInInterval(long A, long B) {
        int res = 0;
        String a = String.valueOf(A);
        String b = String.valueOf(B);
        int af = numberAnalysis(a, true);
        int bf = numberAnalysis(b, false);
        if (a.length() == b.length()) {
            res = bf < af ? 0 : bf - af + 1;
        } else {
            res += 10 + bf - af;
            int m = b.length() - 1;
            int n = a.length() + 1;
            res += (m - n + 1) * 9;
        }
        System.out.println("res=" + res);
        return res;
    }
    private static int numberAnalysis(String num, boolean low) {
        int flag = 0;
        for (int i = 1; i < num.length() && flag == 0; i++) {
            if (num.charAt(i) < num.charAt(0)) {
                flag = -1;
            } else if (num.charAt(i) > num.charAt(0)) {
                flag = 1;
            }
        }
        int f = num.charAt(0) - '0';
        if (!low && flag == -1) {
            f--;
        } else if (low && flag == 1) {
            f++;
        }
        return f;
    }
    public static int getMinimumDeflatedDiscCount(int N, int[] R) {
        int res = 0;
        for (int i = N - 2; i >= 0; i--) {
            if (R[i] >= R[i + 1]) {
                R[i] = R[i + 1] - 1;
                if (R[i] <= 0) {
                    System.out.println(-1);
                    return -1;
                }
                res++;
            }
        }
        System.out.println(res);
        return res;
    }
    public static int getMinProblemCount(int N, int[] S) {
        boolean odd = false;
        int max = S[0];
        for (int i = 1; i < N; i++) {
            max = Math.max(max, S[i]);
            if (S[i] % 2 == 1) odd = true;
        }
        int cnt = max / 2;
        if (max % 2 == 1 || odd) cnt++;
        return cnt;
    }
    private static int calcuateStep(int from, int to, int N) {
        int a = Math.abs(from - to);
        int b = from > to ? N + to - from : from + N - to;
        return Math.min(a, b);
    }
    public static long getMinCodeEntryTime(int N, int M, int[] C) {
        long res = 0;
        int pre = 1;
        for (int i = 0; i < M ; i++) {
            res += calcuateStep(pre, C[i], N);
            pre = C[i];
        }
        System.out.println(res);
        return res;
    }
    public static void getMaximumEatenDishCount(int N, int[] D, int K) {
        LinkedHashSet<Integer> seen = new LinkedHashSet<>();
        int res = 0;
        for (int i = 0; i < N; i++) {
            if (!seen.contains(D[i])) {
                seen.add(D[i]);
                res++;
                if (seen.size() > K) {
                    Optional<Integer> first = seen.stream().findFirst();
                    first.ifPresent(f -> seen.remove(f));
                }
            }
        }
        System.out.println(res);
    }
    public static void level(Node head) {
        Node cur = head;
        List<Integer> res = new ArrayList<>();
        while (cur != null) {
            res.add(cur.val);
            cur = cur.next;
        }
        System.out.println(res);
    }
    public static void connect(Node root) {
        Node first = root, second = new Node(-1), tail = second;
        while (first != null) {
            if (first.left != null) {
                tail.next = first.left;
                tail = tail.next;
            }
            if (first.right != null) {
                tail.next = first.right;
                tail = tail.next;
            }
            first = first.next;
            if (first == null) {
                first = second.next;
                second = new Node(-1);
                tail = second;
            }
        }

    }
    static class Node {
        int val;
        Node left;
        Node right;
        Node next;
        public Node(int value) {
            val = value;
        }
    }
    public static void getArtisticPhotographCount(int N, String C, int X, int Y) {
        char[] c = C.toCharArray();
        int res = 0;
        for (int i = 0; i < N; i++) {
            if (c[i] != 'A') continue;
            int leftP = 0, leftB = 0, rightP = 0, rightB = 0;
            for (int k = i - 1; k >= 0; k--) {
                if (c[k] == 'P' && i - k <= Y && i - k >= X) leftP++;
                else if (c[k] == 'B' && i - k <= Y && i - k >= X) leftB++;
            }
            for (int k = i + 1; k < N; k++) {
                if (c[k] == 'P' && k - i <= Y && k - i >= X) rightP++;
                else if (c[k] == 'B' && k - i <= Y && k - i >= X) rightB++;
            }
            res += leftP * rightB + leftB * rightP;
        }
        System.out.println(res);
    }
}
