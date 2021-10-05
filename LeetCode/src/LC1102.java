import java.util.PriorityQueue;

public class LC1102 {
    public static void main(String ...args) {
        System.out.println(maximumMinimumPath(new int[][]{{2,2,1,2,2,2},{1,2,2,2,1,2}}));
        System.out.println(maximumMinimumPath(new int[][]{{3,4,6,3,4},{0,2,1,1,7},{8,8,3,2,7},{3,2,4,9,8},{4,1,2,0,0},{4,6,5,4,3}}));
    }
    public static int maximumMinimumPath(int[][] A) {
        int [][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int m = A.length;
        int n = A[0].length;
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        maxHeap.add(new int[]{0, 0, A[0][0]});
        boolean[][] visited = new boolean[m][n];
        while (!maxHeap.isEmpty()) {
            int[] cur = maxHeap.poll();
            int cost = cur[2];
            int row = cur[0];
            int col = cur[1];
            if (row == m - 1 && col == n - 1) {
                return cost;
            }
            visited[row][col] = true;
            for (int[] dir : dirs) {
                int x = row + dir[0];
                int y = col + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                maxHeap.add(new int[]{x, y, Math.min(cost, A[x][y])});
            }
        }
        return -1;
    }
}
