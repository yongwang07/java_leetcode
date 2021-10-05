package facebook;

import java.util.*;
import java.util.stream.Collectors;

public class LC226 {
//    https://leetcode.com/discuss/interview-question/790428/Facebook-Phone-Interview-Questions
    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(7);
        root.right.left = new Node(6);
        root.right.right = new Node(9);
        invertTree(root);
        findLengthOfLCIS(new int[]{1,3,5,4,7});
        findLengthOfLCIS(new int[]{2,2,2,2,2});
        nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
        nextGreaterElement(new int[]{2, 4}, new int[]{1,2,3,4});
        nextGreaterElements(new int[]{1,2,1});
        searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        searchRange(new int[]{5,7,7,8,8,10}, 6);
        findInsert(new int[]{5, 7, 7, 9, 9, 10}, 4);
        lengthOfLongestSubstring("abcabcbb");
        lengthOfLongestSubstring("bbbbb");
        lengthOfLongestSubstring("pwwkew");
        employeeFreeTime(new int[][]{{1,2},{5,6},{1,3},{4,10}});
        employeeFreeTime(new int[][]{{1,3},{6,7},{2,4},{2,5},{9,12}});
        allPossibilies("13", 2);
    }
    public static void allPossibilies(String s, int target) {
        Arrays.stream(s.split("")).map(Integer::parseInt).toArray();
    }
    public static void employeeFreeTime(int[][] schedule) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(schedule, (a, b) -> a[0] - b[0]);
        System.out.println(Arrays.stream(schedule)
                .map(interval -> interval[0] + "-" + interval[1]).collect(Collectors.toList()));
        int end = 0;
        for (int[] cur : schedule) {
            if (cur[0] > end) {
                System.out.println(end + "-" + cur[0]);
                end = cur[1];
            } else {
                end = Math.max(cur[1], end);
            }
        }
    }
    public static void lengthOfLongestSubstring(String s) {
        Map<Character, Integer> m = new HashMap<>();
        int left = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (m.get(s.charAt(i)) == null /*|| m.get(s.charAt(i)) < left*/) {
                res = Math.max(res, i - left + 1);
            } else {
                left = i;
            }
            m.put(s.charAt(i), i);
        }
        System.out.println(res);
    }
    public static void findInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left + ":" + right + ":" + res);
    }
    public static void searchRange(int[] nums, int target) {
        int[] range = new int[]{-1, -1};
        for (int i = 0; i < 2; i++) {
            int left = 0, right = nums.length - 1, res = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    res = mid;
                    if (i == 0) right = mid - 1;
                    else left = mid + 1;
                } else if (nums[mid] < target) left = mid + 1;
                else right = mid - 1;
            }
            range[i] = res;
            if (res == -1) break;
        }
        if (range[1] == -1) range[1] = range[0];
        System.out.println(Arrays.toString(range));
    }
    public static void nextGreaterElements(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        for (int i = 0; i < 2 * nums.length; i++) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i % nums.length]) {
                res[st.pop()] = nums[i % nums.length];
            }
            if (i < nums.length) st.push(i);
        }
        System.out.println(Arrays.toString(res));
    }
    public static void nextGreaterElement(int[] findNums, int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        Stack<Integer> st = new Stack<>();
        for (int num : nums) {
            while (!st.isEmpty() && st.peek() < num) {
                m.put(st.pop(), num);
            }
            st.push(num);
        }
        int[] res = Arrays.stream(findNums).map(num -> m.getOrDefault(num, -1)).toArray();
        System.out.println(Arrays.toString(res));
    }
    public static void findLengthOfLCIS(int[] nums) {
        int res = 0, len = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] > nums[i - 1]) {
                len++;
                res = Math.max(res, len);
            } else len = 1;
        }
        System.out.println(res);
    }
    public static void invertTree(Node root) {
        if (root == null) return;
        invertTree(root.left);
        invertTree(root.right);
        Node t = root.left;
        root.left = root.right;
        root.right = t;
    }
    private static void visit(Node n) {
        if (n == null) return;
        visit(n.left);
        System.out.print(n.value + ",");
        visit(n.right);
    }
    static class Node {
        int value;
        Node left, right;
        public Node(int value) {
            this.value = value;
        }
    }
}