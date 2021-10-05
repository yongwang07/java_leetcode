import java.util.Arrays;

public class LC1135 {
    public static void main(String[] args) {
        minimumCost(3, new int[][]{{1,2,5},{1,3,6},{2,3,1}});
        minimumCost(4, new int[][]{{1,2,3},{3,4,4}});
    }

    public static int find(int i, int[] root) {
        if (i == root[i]) {
            return i;
        }
        return find(root[i], root);
    }

    public static void minimumCost(int N, int[][] connections) {
        int[] root = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            root[i] = i;
        }
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        int edges = 0, total = 0;
        for (int[] connection : connections) {
            int start = connection[0];
            int end = connection[1];
            int cost = connection[2];
            int startRoot = find(start, root);
            int endRoot = find(end, root);
            if (startRoot != endRoot) {
                root[start] = endRoot;
                edges++;
                total += cost;
                if (edges == N - 1) break;
            }
        }
        if (edges == N - 1) {
            System.out.println("total=" + total);
        } else {
            System.out.println("No Path");
        }
    }
}
