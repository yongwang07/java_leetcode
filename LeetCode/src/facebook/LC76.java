package facebook;

import java.util.*;

public class LC76 {
    public static void main(String[] args) {
        minWindow("ADOBECODEBANC", "ABC"); //BANC
        minAddToMakeValid("()))((");
        minAddToMakeValid("(((");
        removeDuplicate("bbaab");
        removeDuplicate("abcd");
        int a = findPeak(new int[]{5, 10, 20, 15}, 0, 3);
        System.out.println(a);
        findDiagonalOrder(new int[][]{{1, 2, 3, 4, 5}, {6, 7}, {8}, {9, 10, 11}, {12, 13, 14, 15, 16}});
        findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        rightSideView(root);
        pickIndex(new int[]{1, 3, 5});
    }
    public static void pickIndex(int[] w) {
        System.out.println("+++++++++");
        TreeMap<Integer, Integer> res = new TreeMap();
        res.put(w[0], 0);
        for (int i = 1; i < w.length; i++) {
            res.put(w[i] + w[i - 1], i);
        }
        System.out.println(res + "." + res.lastKey());
        Random rand = new Random();
        int value = rand.nextInt(res.lastKey()) + 1;
        int key = res.ceilingKey(value);
        int i = Arrays.binarySearch(w, value);
        System.out.println(value + "?????" + i);
        System.out.println(value + "<" + key + "=>" + res.get(key));
    }
    static class TreeNode {
        int value;
        TreeNode left, right;
        public TreeNode(int value) {
            this.value = value;
        }
    }
    public static void rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode lastNode = null;
            for (int i = q.size(); i > 0; i--) {
                lastNode = q.poll();
                if (lastNode.left != null) {
                    q.offer(lastNode.left);
                }
                if (lastNode.right != null) {
                    q.offer(lastNode.right);
                }
            }
            res.add(lastNode.value);
        }
        System.out.println(res);
    }
    public static void findDiagonalOrder(int[][] nums) {
        List<Integer> res = new ArrayList<>();
        int colMax = Arrays.stream(nums).map(num -> num.length).reduce(0, (max, len) -> Math.max(max, len));
        for (int i = 0; i < nums.length + colMax - 1; i++) {
            int x = i < nums.length ? i : nums.length - 1;
            int y = i < nums.length ? 0 : i - nums.length + 1;
            while (x >= 0) {
                if (y < nums[x].length) {
                    res.add(nums[x][y]);
                }
                x--;
                y++;
            }
        }
        System.out.println(res);
    }
    public static int findPeak(int[] arr, int left, int right) {
        int mid = left + (right - left) / 2;
        if ((mid == 0 || arr[mid - 1] <= arr[mid])
                && (mid == arr.length - 1 || arr[mid + 1] <= arr[mid])) {
            return arr[mid];
        } else if (mid > 0 && arr[mid - 1] > arr[mid])
            return findPeak(arr, left, (mid - 1));
        else
            return findPeak(
                    arr, (mid + 1), right);
    }
    public static void removeDuplicate(String s) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0, i = 0;
        while (i < s.length()) {
            for (;i + 1 < s.length() && s.charAt(i + 1) == s.charAt(i); i++, cnt++);
            if (cnt == 0) sb.append(s.charAt(i));
            else cnt = 0;
            i++;
        }
        System.out.println(sb.toString());
    }
    public static void minAddToMakeValid(String s) {
        int left = 0, right = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++right;
            } else if (right > 0) {
                --right;
            } else {
                ++left;
            }
        }
        System.out.println(left + right);
    }
    public static void minWindow(String s, String t) {
        Map<Character, Integer> m = new HashMap<>();
        for (char c : t.toCharArray()) {
            m.put(c, m.getOrDefault(c, 0) + 1);
        }
        int left = 0, count = 0, right = 0, minLen = 1000, start = 0;
        while (right < s.length()){
            char c = s.charAt(right);
            if (m.containsKey(c)) {
                if (m.get(c) > 0) count++;
                m.put(c, m.get(c) - 1);
            }
            while (count == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }
                char r = s.charAt(left);
                if (m.containsKey(r)) {
                    m.put(r, m.get(r) + 1);
                    if (m.get(r) > 0) count--;
                }
                left++;
            }
            right++;
        }
        System.out.println(s.substring(start, start + minLen));
    }
}
