import java.util.*;

public class LC140 {
    public static void main(String[] args) {
        wordBreak("pineapplepenapple", new String[]{"apple", "pen", "applepen", "pine", "pineapple"});
        wordBreak("catsanddog", new String[]{"cat", "cats", "and", "sand", "dog"});
        wordBreak("catsandog", new String[]{"cat", "cats", "and", "sand", "dog"});
        intToRoman(1994);
        System.out.println("+  42=" + myAtoi("+  42"));
        System.out.println("   -42" + "=" + myAtoi("   -42"));
        System.out.println("words and 987" + "=" + myAtoi("words and 987"));
        System.out.println("4193 with words" + "=" + myAtoi("4193 with words"));
        System.out.println("-91283472332" + "=" + myAtoi("-91283472332"));
        System.out.println("991283472332" + "=" + myAtoi("991283472332"));
        System.out.println("2147483647=" + myAtoi("2147483647"));
    }
    public static int myAtoi(String str) {
        int num = 0, flag = 1;
        for (char c : str.toCharArray()) {
            if (c == ' ' || c == '+') continue;
            if (c == '-') {
                flag = -1;
                continue;
            }
            if (!Character.isDigit(c)) return num;
            int pre = num;
            num = 10 * num + (c - '0');
            if (flag > 0 && num < pre) return Integer.MAX_VALUE;
             else if (flag < 0 && num > pre) return Integer.MIN_VALUE;
        }
        return flag * num;
    }
    public static void intToRoman(int num) {
        char[] roman = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int[] value = {1000, 500, 100, 50, 10, 5, 1};
        String res = "";
        for (int n = 0; n < 7; n += 2) {
            int x = num / value[n];
            if (x < 4) {
                for (int i = 1; i <= x; ++i) res += roman[n];
            } else if (x == 4) {
                res = res + roman[n] + roman[n - 1];
            } else if (x > 4 && x < 9) {
                res += roman[n - 1];
                for (int i = 6; i <= x; ++i) res += roman[n];
            } else if (x == 9) {
                res = res + roman[n] + roman[n - 2];
            }
            num %= value[n];
        }
        System.out.println(res);
    }

    public static List<String> dfs(String s, String[] dict, Map<String, List<String>> memo) {
        if (s.equals("")) {
            return new ArrayList<>(Arrays.asList(""));
        }
        if (memo.containsKey(s)) return memo.get(s);
        List<String> res = new ArrayList<>();
        for (String word : dict) {
            if (s.startsWith(word)) {
                List<String> result = dfs(s.substring(word.length()), dict, memo);
                for (String ss : result) {
                    res.add(ss.equals("") ? word : word + " " + ss);
                }
            }
        }
        memo.put(s, res);
        return res;
    }
    public static void wordBreak(String s, String[] wordDict) {
        Map<String, List<String>> memo = new HashMap<>();
        dfs(s, wordDict, memo);
        System.out.println(memo.get(s));
    }
}
