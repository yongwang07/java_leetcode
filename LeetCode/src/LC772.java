import java.util.*;

public class LC772 {
    public static void main(String[] args) {
        calculate("1 + 1");
        calculate(" 6-4 / 2 ");
        calculate("2*(5+5*2)/3+(6/2+8)"); //21
        calculate("(2+6* 3+5- (3*14/7+2)*5)+3"); //-12
    }
    public static void calculate(String s) {
        List<String> tokens = tokenParse(s);
        Stack<Integer> num = new Stack<>();
        Stack<String> op = new Stack<>();
        for (String token : tokens) {
            if (token.equals("(")) {
                op.push(token);
            } else if (token.equals(")")) {
                while (!op.peek().equals("(")) {
                    num.push(cal(num.pop(), num.pop(), op.pop()));
                }
                op.pop();
            } else if (token.equals("+") || token.equals("-")) {
                while (!op.isEmpty() && "+,-,*,/".indexOf(op.peek()) >= 0) {
                    num.push(cal(num.pop(), num.pop(), op.pop()));
                }
                op.push(token);
            } else if (token.equals("*") || token.equals("/")) {
                while (!op.isEmpty() && "*,/".indexOf(op.peek()) >= 0) {
                    num.push(cal(num.pop(), num.pop(), op.pop()));
                }
                op.push(token);
            } else {
                num.push(Integer.parseInt(token));
            }
        }
        while (!op.isEmpty()) {
            num.push(cal(num.pop(), num.pop(), op.pop()));
        }
        System.out.println(num.pop());
    }
    public static int cal(int n, int m, String op) {
        if (op.equals("+")) return m + n;
        if (op.equals("-")) return m - n;
        if (op.equals("*")) return m * n;
        return m / n;
    }
    public static List<String> tokenParse(String s) {
        List<String> tokens = new ArrayList<>();
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                res += c;
            } else {
                if (res.length() > 0) {
                    tokens.add(res);
                    res = "";
                }
                tokens.add(c + "");
            }
        }
        if (res.length() > 0) tokens.add(res);
        return tokens;
    }
}
