import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC567 {
    public static void main(String[] args) {
//        boolean a = checkInclusion("ab", "eidbaooo");
//        System.out.println(a);
//        a = checkInclusion("ab", "eidboaoo");
//        System.out.println(a);
        minWindow("ADOBECODEBANC", "ABC"); //BANC
    }
    public static void minWindow(String s1, String s2) {
        String res = "";
        Map<Character, Integer> m = new HashMap<>();
        int left = 0, cnt = 0, minLen = s1.length() + 1;
        for (char c : s2.toCharArray()) m.put(c, m.getOrDefault(c, 0) + 1);
        for (int i = 0; i < s1.length(); i++) {
            if (m.get(s1.charAt(i)) != null) {
                int n = m.get(s1.charAt(i)) - 1;
                if (n >= 0) cnt++;
                m.put(s1.charAt(i), n);
            }
            while (cnt == s2.length()) {
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    res = s1.substring(left, i + 1);
                }
                if (m.get(s1.charAt(left)) != null) {
                    int k = m.get(s1.charAt(left)) + 1;
                    if (k > 0) --cnt;
                    m.put(s1.charAt(left), k);
                }
                ++left;
            }
        }
        System.out.println(res);
    }
    public static boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length(), cnt = n1, left = 0;
        int[] m = new int[128];
        for (char c : s1.toCharArray()) m[c - 'a']++;
        for (int right = 0; right < n2; right++) {
            if (m[s2.charAt(right) - 'a']-- > 0) --cnt;
            while (cnt == 0) {
                if (right - left + 1 == n1) return true;
                if (++m[s2.charAt(left++) - 'a'] > 0) ++cnt;
            }
        }
        return false;
    }
}