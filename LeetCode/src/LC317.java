import java.util.LinkedList;
import java.util.Queue;

public class LC317 {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        shortestDistance(grid);
    }
    public static void shortestDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length, buildCnt = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int[][] cnt = new int[m][n];
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) continue;
                buildCnt++;
                boolean[][] visited = new boolean[m][n];
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{i, j});
                int level = 1;
                while (!q.isEmpty()) {
                    for (int k = q.size(); k > 0; k--) {
                        int[] cur = q.poll();
                        for (int[] dir : dirs) {
                            int x = dir[0] + cur[0];
                            int y = dir[1] + cur[1];
                            if (x < 0 || x >= m || y < 0 || y >= n) continue;
                            if (visited[x][y] || grid[x][y] == 2 || grid[x][y] == 1) continue;
                            dist[x][y] += level;
                            cnt[x][y] += 1;
                            visited[x][y] = true;
                            q.add(new int[]{x, y});
                        }
                    }
                    level++;
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cnt[i][j] == buildCnt) {
                    res = Math.min(res, dist[i][j]);
                }
            }
        }
        System.out.println("res=" + res);
    }
}
