package facebook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LC1428 {
    public static void main(String[] args) {
        leftMostColumnWithOne(new int[][]{{0, 0, 0, 1}, {0, 0, 1, 1}, {0, 1, 1, 1}});
        findContinuous(new int[]{2, 11, 3, 4, 0, 1, 2, 9});
        threeSumSmaller(new int[]{-2, 0, 1, 3}, 2);
        threeSumSmaller(new int[]{5, 1, 3, 4, 7}, 12);
        validTicTacToe(new String[]{"O  ", "   ", "   "});
        validTicTacToe(new String[]{"XOX", " X ", "   "});
        validTicTacToe(new String[]{"XXX", "   ", "OOO"});
        validTicTacToe(new String[]{"XOX", "O O", "XOX"});
        validTicTacToe(new String[]{"XXX", "OOO", "O  "});
    }
    public static void validTicTacToe(String[] pos) {
        int xCnt = 0, oCnt = 0;
        int[] rowCnt = new int[3];
        int[] colCnt = new int[3];
        int dig = 0, reDig = 0;
        for (int i = 0; i < pos.length; i++) {
            char[] cols = pos[i].toCharArray();
            for (int j = 0; j < 3; j++) {
                if (cols[j] == 'O') {
                    oCnt++;
                    rowCnt[i] += 1;
                    colCnt[j] += 1;
                    if (i == j) dig += 1;
                    if (i + j == 2) reDig += 1;
                } else if (cols[j] == 'X') {
                    xCnt++;
                    rowCnt[i] += -1;
                    colCnt[j] += -1;
                    if (i == j) dig += 1;
                    if (i + j == 2) reDig += 1;
                }
            }
        }
        System.out.println("+++++++++++++");
        if (xCnt == 0 && oCnt == 1) {
            System.out.println("False");
        } else if (xCnt - oCnt > 1) {
            System.out.println("False");
        } else {
            boolean complete = Math.abs(rowCnt[0]) == 3 || Math.abs(rowCnt[1]) == 3 || Math.abs(rowCnt[2]) == 3
            || Math.abs(colCnt[0]) == 3 || Math.abs(colCnt[1]) == 3 || Math.abs(colCnt[2]) == 3 || Math.abs(dig) == 3
                    || Math.abs(reDig) == 3;
            if (complete) {
                System.out.println("False");
            } else {
                System.out.println("True");
            }
        }
    }
    public static void threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int n = nums[i], left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] < target - n) {
                    res += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        System.out.println("total smaller=" + res);
    }
    public static void findContinuous(int[] arr) {
        Set<Integer> all = new HashSet<>();
        Arrays.stream(arr).forEach(i -> all.add(i));
        int res = 0;
        for (int i : arr) {
            int m = 0, k = i;
            while (all.contains(k)) {
                all.remove(k);
                m++;
                k--;
            }
            k = i + 1;
            while (all.contains(k)) {
                all.remove(k);
                m++;
                k++;
            }
            res = Math.max(res, m);
        }
        System.out.println(res);
    }
    public static boolean helper(int[][] matrix, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (matrix[i][col] == 1) return true;
        }
        return false;
    }
    public static void leftMostColumnWithOne(int[][] matrix) {
        int left = 0, right = matrix[0].length, res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (helper(matrix, matrix.length, mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println("leftMost=" + res);
    }
}
