package facebook;

import java.util.*;

public class LC125 {
    public static void main(String[] args) {
//        System.out.println(isPalindrome("race a car"));
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        nextPermutation(new int[] {1, 2, 7, 4, 3, 1});
        nextPermutation(new int[] {3, 2, 1});
        findBuildings(new int[]{4, 2, 3, 1});
        findBuildings(new int[]{2, 2, 2, 2});
        intervalIntersection(new int[][]{{0, 2},{5, 10}, {13, 23}, {24, 25}},
                new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}});
        combinationSum(new int[]{2, 3, 5}, 8);
        System.out.println(compareStr("aaaabbbabc", "a4b3abc"));
        generateAbbreviations("word");
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        rightSideView(root);
    }
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) {
          this.val = x;
      }
    }
    public static void rightSideView(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
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
            res.add(lastNode.val);
        }
        System.out.println(res);
    }
    public static void generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (int j = i; j < word.length(); j++) {
                String w = word.substring(0, i) + (j - i + 1) + word.substring(j + 1);
                res.add(w);
            }
        }
        System.out.println(res);
    }

    public static boolean compareStr(String a, String b) {
        int i = 0, j = 0, cnt = 0;
        while (i < a.length() && j < b.length()) {
            char aC = a.charAt(i), bC = b.charAt(j);
            if (Character.isDigit(bC)) {
                cnt = 10 * cnt + (bC - '0');
                j++;
            } else if (cnt > 0) {
                while (cnt-- > 0) {
                    if (aC != a.charAt(i++)) return false;
                }
            } else if (aC == bC) {
                i++;
                j++;
            } else return false;
        }
        return true;
    }
    public static void dfs(int idx, int[] arr, int target, List<Integer> res, List<List<Integer>> all) {
        if (target == 0) {
            all.add(new ArrayList<>(res));
            return;
        }
        if (arr[idx] > target) return;
        for (int i = idx; i < arr.length; i++) {
            res.add(arr[i]);
            dfs(i, arr, target - arr[i], res, all);
            res.remove(res.size() - 1);
        }
    }
    public static void combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> all = new ArrayList<>();
        dfs(0, candidates, target, res, all);
        System.out.println(all);
    }
    public static void intervalIntersection(int[][] a, int[][] b) {
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            int aL = a[i][0], aR = a[i][1], bL = b[i][0], bR = b[i][1];
            int left = Math.max(aL, bL), right = Math.min(aR, bR);
            if (left <= right) res.add(new int[]{left, right});
            if (aR < bR) ++i;
            else ++j;
        }
        System.out.println(res);
    }
    public static void findBuildings(int[] heights) {
        LinkedList<Integer> q = new LinkedList<>();
        q.addFirst(heights.length - 1);
        int max = heights[heights.length - 1];
        for (int i = heights.length - 2; i >= 0; i--) {
            if (heights[i] > max) {
                q.addFirst(i);
                max = heights[i];
            }
        }
        System.out.println(q);
    }
    public static void swap(int i, int j, int[] num) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
    public static void nextPermutation(int[] nums) {
        int n = nums.length, i = n - 2, j = n - 1;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            while (nums[j] <= nums[i]) j--;
            swap(i, j, nums);
        }
        for (i = i + 1, j = n - 1; i < j; i++, j--) {
            swap(i, j, nums);
        }
        System.out.println(Arrays.toString(nums));
    }
    public static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char a = Character.toLowerCase(s.charAt(left)), b = Character.toLowerCase(s.charAt(right));
            if (!Character.isLetter(a)) {
                left++;
            } else if (!Character.isLetter(b)) {
                right--;
            } else {
                if (a != b) return false;
                left++;
                right--;
            }
        }
        return true;
    }
}
