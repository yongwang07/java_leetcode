import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC886 {
    public static void main(String ...args) {
        System.out.println(possibleBipartition(4, new int[][]{{1, 2}, {1, 3}, {2, 4}}));
        System.out.println(possibleBipartition(3, new int[][]{{1, 2}, {1, 3}, {2, 3}}));
        System.out.println(possibleBipartition(5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}}));
    }
    private static boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, Integer> assign = new HashMap<>();
        for (int[] dislike : dislikes) {
            Integer a = assign.get(dislike[0]);
            Integer b = assign.get(dislike[1]);
            if (a != null && b != null) {
                if (a == b) return false;
            } else if (a == null && b != null) {
                assign.put(dislike[0], 1 - b);
            } else if (a != null && b == null) {
                assign.put(dislike[1], 1 - a);
            } else {
                assign.put(dislike[0], 0);
                assign.put(dislike[1], 1);
            }
        }
        return true;
    }

}
