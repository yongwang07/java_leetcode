import java.util.Arrays;

public class LC498 {
    public static void main(String[] args) {
        findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        printCircle(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        printCircle(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}});
        printCircle(new int[][]{{1, 2, 3, 4}});
        matrixRotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }
    public static void findDiagonalOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = 0;
        int[] res = new int[m * n];
        for (int i = 0; i < res.length; i++) {
            System.out.println(matrix[row][col]);
            if ((row + col) % 2 == 1) {
                if (row == m - 1) {
                    col++;
                } else if (col == 0) {
                    row++;
                }
                else {
                    row++;
                    col--;
                }
            } else {
                if (col == n - 1) {
                    row++;
                } else if (row == 0) {
                    col++;
                } else {
                    row--;
                    col++;
                }
            }
        }
    }
    public static void printCircle(int[][] matrix) {
        int left = 0, col = 0, right = matrix[0].length - 1, top = 0, bottom = matrix.length - 1;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                System.out.println(matrix[top][i]);
            }
            top++;
            if (top > bottom) break;
            for (int j = top; j <= bottom; j++) {
                System.out.println(matrix[j][right]);
            }
            right--;
            if (left > right) break;
            for (int k = right; k >= left; k--) {
                System.out.println(matrix[bottom][k]);
            }
            bottom--;
            if (top > bottom) break;
            for (int q = bottom; q >= top; q--) {
                System.out.println(matrix[q][col]);
            }
            left++;
        }
    }
    public static void matrixRotate(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] res = new int[n][m];
        int aCol = m - 1;
        for (int row = 0; row < m; row++) {
            int aRow = 0;
            for (int col = 0; col < n; col++) {
                res[aRow++][aCol] = matrix[row][col];
            }
            aCol--;
        }
        for (int[] a : res) System.out.println(Arrays.toString(a));
    }
}
