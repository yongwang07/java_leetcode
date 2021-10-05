package facebook;

import java.util.*;
import java.util.stream.Collectors;

public class Karat {
    public static void main(String[] args) {
        System.out.println("indeed Karat");
        int a = calc("4+3-9");
        System.out.println(a);
        a = calc("(33-3)+((8-3)-(3-88))"); // 120
        System.out.println(a);
        Map<String, Integer> m = new HashMap<>();
        m.put("a", 2);
        m.put("d", 1);
        calc2("(a-4)+b-(c-(d+6)-k)", m);
        fitColumn("it is a very nice day", 10);
        fitColumn("it is a very nice day", 15);
        reflowAndJustify(new String[]{"The day began as still as the",
                "night abruptly lighted with", "brilliant flame"}, 24);
    }
    private static String fill(int i, int j, List<String> words, int col) {
        String[] res = new String[j - i + 1];
        int idx = 0;
        for (int k = i; k <= j; k++) res[idx++] = words.get(k);
        return String.join("-", res);
    }
    private static String fill2(int i, int j, List<String> words, int col) {
        int len = 0;
        for (int k = i; k <= j; k++) len += words.get(k).length() + (len > 0 ? 1 : 0);
        int exSpace = col - len;
        StringBuilder sb = new StringBuilder();
        sb.append(words.get(i));
        for (int k = i + 1; k <= j; k++) {
            sb.append("-");
            if (exSpace-- > 0) sb.append("-");
            sb.append(words.get(k));
        }
        return sb.toString();
    }

    public static void reflowAndJustify(String[] lines, int colWidth) {
        final List<String> words = Arrays.stream(lines).map(line -> line.split(" "))
                .flatMap(x -> Arrays.stream(x)).collect(Collectors.toList());
        System.out.println(words);
        List<String> res = new ArrayList<>();
        int len = 0, start = 0;
        for (int i = 0; i < words.size(); i++) {
            int newLen = len + words.get(i).length() + (len > 0 ? 1 : 0);
            if (newLen <= colWidth) {
                len = newLen;
            } else {
                res.add(fill2(start, i - 1, words, colWidth));
                start = i--;
                len = 0;
            }
        }
        res.add(fill2(start, words.size() - 1, words, colWidth));
        System.out.println(res);
    }
    public static void fitColumn(String s, int colWidth) {
        int start = 0;
        while (start < s.length()) {
            if (start + colWidth < s.length()) {
                int k = start + colWidth;
                for (; s.charAt(k) != ' '; k--);
                System.out.println("|" + s.substring(start, k + 1) + "|");
                start = k + 1;
            } else {
                System.out.println("|" + s.substring(start) + "|");
                break;
            }
        }
    }
    static class Result {
        int value;
        List<String> unResult;
        public Result(int value, List<String> unResult) {
            this.value = value;
            this.unResult = unResult;
        }
    }
    public static Result calc2(String s, Map<String, Integer> m) {
        int n = 0, op = 1, res = 0;
        String var = "";
        List<String> unResolve = new ArrayList<>();
        for (int i = 0; i <= s.length(); i++) {
            char c = i < s.length() ? s.charAt(i) : ' ';
            if (i == s.length() || c == '+' || c == '-') {
                if (n > 0) res += op * n;
                else if (var.length() > 0) {
                    if (m.get(var) != null) res += op * m.get(var);
                    else unResolve.add(op == -1 ? "-" + var : "+" + var);
                }
                n = 0;
                var = "";
                op = c == '-' ? -1 : 1;
            } else if (c == '(') {
                int cnt = 1, k = i + 1;
                for (; k < s.length(); k++) {
                    if (s.charAt(k) == '(') cnt++;
                    else if (s.charAt(k) == ')') cnt--;
                    if (cnt == 0) break;
                }
                String sub = s.substring(i + 1, k);
                Result r = calc2(sub, m);
                res += op * r.value;
                for (String v : r.unResult) {
                    if (op == -1) {
                        unResolve.add((v.charAt(0) == '+' ? '-' : '+') + v.substring(1));
                    }
                }
                i = k;
            } else {
                if (Character.isDigit(c)) {
                    n = 10 * n + (c - '0');
                } else{
                    var += c;
                }
            }
        }
        System.out.println(res + "," + unResolve);
        return new Result(res, unResolve);
    }
    public static int calc(String s) {
        int n = 0, op = 1, res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                int cnt = 1, k = i + 1;
                for (; k < s.length(); k++) {
                    if (s.charAt(k) == '(') cnt++;
                    else if (s.charAt(k) == ')') cnt--;
                    if (cnt == 0) break;
                }
                res += op * calc(s.substring(i + 1, k));
                i = k;
            } else if (Character.isDigit(c)) {
                n = 10 * n + (c - '0');
            } else if (c == '-' || c == '+') {
                res += op * n;
                n = 0;
                op = c == '-' ? -1 : 1;
            }
        }
        res += op * n;
        return res;
    }
}
