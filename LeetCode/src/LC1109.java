import java.util.Arrays;

public class LC1109 {
    public static void main(String ...args) {
        int[] answer = corpFlightBookings(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5);
        System.out.println(Arrays.toString(answer)); //[10,55,45,25,25]
        answer = corpFlightBookings(new int[][]{{1, 2, 10}, {2, 2, 15}}, 2);
        System.out.println(Arrays.toString(answer)); // [10, 25]
    }

    private static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] booking: bookings) {
            res[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                res[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }
}
