package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC223 {
    public static void main(String[] args) {
        computeArea(-3,0, 3, 4, 0, -1, 9, 2);
        sortByOneSwap(new int[]{1, 2, 6, 2, 3, 4, 2});
        multiply("123", "456");
        multiply("99", "9");
        boolean same = validWordAbbreviation("internationalization", "i12iz4n");
        System.out.println(same);
        same = validWordAbbreviation("apple", "a2e");
        System.out.println(same);
        Node head = new Node(2);
        head.next = new Node(4);
        head.next.next = new Node(6);
        head.next.next.next = head;
        head = insert(head, 1);
        visit(head);
        findMissingRange(new int[]{2, 4, 7, 13, 20});
        getMaxAdditionalDinersCount(10, 1, 2, new long[]{2, 6});
        getMaxAdditionalDinersCount(15, 2, 3, new long[]{11, 6, 14});
    }
    private static long fillGap(long left, long right, long k) {
        if (right - left <= 1) return 0;
        long range = right - left - 1;
        long cnt = range / (k + 1);
        if (range % (k + 1) < k) cnt -= 1;
        return cnt;
    }
    public static void getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
        long res = 0;
        Arrays.sort(S);
        if (2 + K <= S[0]) res++;
        res += fillGap(1, S[0], K);
        for (int i = 1; i < M; i++) {
            res += fillGap(S[i - 1], S[i], K);
        }
        if (S[M - 1] + K + 1 <= N) res++;
        res += fillGap(S[M - 1], N, K);
        System.out.println(res);
    }
    public static void fill(int left, int right, List<String> res) {
        if (right - left - 1 >= 3) res.add((left + 1) + "-" + (right - 1));
        else {
            for (int i = left + 1; i <= right - 1; i++) {
                res.add(String.valueOf(i));
            }
        }
    }
    public static void findMissingRange(int[] arr) {
        List<String> res = new ArrayList<>();
        if (arr[0] != 0) {
            fill(-1, arr[0], res);
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                fill(arr[i - 1], arr[i], res);
            }
        }
        if (arr[arr.length - 1] != 99) {
            fill(arr[arr.length - 1], 99, res);
        }
        System.out.println(res);
    }
    public static void visit(Node head) {
        Node cur = head;
        List<Integer> res = new ArrayList<>();
        do {
            res.add(cur.value);
            cur = cur.next;
        } while (cur != head);
        System.out.println(res);
    }
    public static Node insert(Node head, int insertVal) {
        Node newNode = new Node(insertVal);
        if (head == null) {
            head = newNode;
            newNode.next = newNode;
            return head;
        }
        Node pre = head, cur = head.next;
        while (pre.next != head) {
            if (pre.value < insertVal && insertVal <= cur.value) break;
            pre = cur;
            cur = cur.next;
        }
        newNode.next = cur;
        pre.next = newNode;
        return insertVal <= head.value ? newNode : head;
    }
    public static boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                if (abbr.charAt(j) == '0') return false;
                int val = 0;
                while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                    val = val * 10 + abbr.charAt(j++) - '0';
                }
                i += val;
            } else {
                if (word.charAt(i++) != abbr.charAt(j++)) return false;
            }
        }
        return i == word.length() && j == abbr.length();
    }
    static class Node {
        Node next;
        int value;
        public Node(int value) {
            this.value = value;
        }
    }
    public static void multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length() - 1];
        int carry = 0;
        for (int a = num2.length() - 1; a >= 0; a--) {
            for (int b = num1.length() - 1; b >= 0; b--) {
                int sum = (num2.charAt(a) - '0') * (num1.charAt(b) - '0') + carry + res[a + b];
                res[a + b] = sum % 10;
                carry = sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        if (carry > 0) sb.append(carry);
        for (int i : res) sb.append(i);
        System.out.println(sb);
    }
    public static void sortByOneSwap(int[] arr) {
        int first = -1, second = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                if (first == -1) {
                    first = i - 1;
                }
                second = i;
            }
        }
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
        System.out.println(Arrays.toString(arr));
    }
    public static void computeArea( int A, int B, int C, int D, int E, int F, int G, int H) {
        int sum1 = (C - A) * (D - B);
        int sum2 = (H - F) * (G - E);
        if (E >= C || F >= D || A >= G || B >= H) {
            System.out.println(sum1 + sum2);
            return;
        }
        int a = sum1 - (Math.min(G, C) - Math.max(A, E)) * (Math.min(D, H) - Math.max(B, F)) + sum2;
        System.out.println(a);
    }
}
