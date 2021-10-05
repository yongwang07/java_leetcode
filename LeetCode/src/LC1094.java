import java.util.*;

public class LC1094 {
    public static void main(String ...args) {
        System.out.println(carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4));
        System.out.println(carPooling(new int[][]{{2,1,5}, {3, 3, 7}}, 5));
        System.out.println(carPooling(new int[][]{{2,1,5}, {3, 5, 7}}, 3));
        System.out.println(carPooling(new int[][]{{3, 2, 7}, {3, 7, 9}, {8, 3, 9}}, 11));
    }
    private static boolean carPooling(int[][] trips, int capacity) {
        int cur = 0;
        List<int[]> data = new ArrayList<>();
        for (int[] trip : trips) {
            data.add(new int[]{trip[1], trip[0]});
            data.add(new int[]{trip[2], -trip[0]});
        }
        Collections.sort(data, Comparator.comparingInt(a -> a[0]));
        for (int[] trip : data) {
            cur += trip[1];
            if (cur > capacity) return false;
        }
        return true;
    }
}
