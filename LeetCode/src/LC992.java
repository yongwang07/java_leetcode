import java.util.HashMap;
import java.util.Map;

public class LC992 {
    public static void main(String[] args) {
        subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2); // 7
        subarraysWithKDistinct(new int[]{1, 2, 1, 3, 4}, 3); // 3
    }
    public static void subarraysWithKDistinct(int[] A, int K) {
        System.out.println(help(A, K) - help(A, K - 1));
    }
    public static int help(int[] A, int K) {
        int n = A.length, res = 0, left = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
            while (map.size() > K) {
                if (map.get(A[left]) == 1) {
                    map.remove(A[left]);
                } else {
                    map.put(A[left], map.get(A[left]) - 1);
                }
                left++;
            }
            res += i - left + 1;
        }
        return res;
    }
}
