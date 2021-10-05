package facebook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCFB2 {
    public static void main(String[] args) {
        getArtisticPhotographCount(5, "APABA", 1, 2); // 1
        getArtisticPhotographCount(5, "APABA", 2, 3); // 0
        getArtisticPhotographCount(8, ".PBAAP.B", 1, 3); // 3
        getSecondsRequired(3, 1, new long[]{1});
        getSecondsRequired(6, 3, new long[]{5, 2, 4});
    }
    public static void getSecondsRequired(long N, int F, long[] P) {
        if(P.length == 1) {
            System.out.println(N - P[0]);
            return;
        }
        Arrays.sort(P);
        int groupSize = 1;
        long ans = 0, currPos = P[0];
        for(int i = 1; i < P.length; i++) {
            ans += P[i] - currPos - 1;
            currPos = P[i];
            groupSize++;
        }
        ans += N - P[P.length - 1] + groupSize - 1;
        System.out.println(ans);
    }
    public static long getArtisticPhotographCount(int N, String C, int X, int Y) {
        long[] preCntP = new long[N];
        long[] preCntB = new long[N];
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (C.charAt(i) == 'A') ids.add(i);
            preCntB[i] = (i > 0 ? preCntB[i - 1] : 0) + (C.charAt(i) == 'B' ? 1 : 0);
            preCntP[i] = (i > 0 ? preCntP[i - 1] : 0) + (C.charAt(i) == 'P' ? 1: 0);
        }
        long res = 0;
        for (int i : ids) {
            long leftP = 0, leftB = 0, rightP = 0, rightB = 0;
            if (i - X >= 0) {
                int end = Math.max(i - Y, 0);
                leftB = preCntB[i - X] - (end == 0 ? 0 : preCntB[end - 1]);
                leftP = preCntP[i - X] - (end == 0 ? 0 : preCntP[end - 1]);
            }
            if (leftB == 0 && leftP == 0) continue;
            if (i + X < N) {
                final int min = Math.min(N - 1, i + Y);
                rightB = preCntB[min] - preCntB[i + X - 1];
                rightP = preCntP[min] - preCntP[i + X - 1];
            }
            res += leftP * rightB + leftB * rightP;
        }
        System.out.println("res=" + res);
        return res;
    }
}
