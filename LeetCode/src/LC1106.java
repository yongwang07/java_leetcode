import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LC1106 {
    public static void main(String ...args) {
        System.out.println(parseBoolExpr("!(f)"));
        System.out.println(parseBoolExpr("|(f,t)"));
        System.out.println(parseBoolExpr("&(t,f)"));
        System.out.println(parseBoolExpr("|(&(t,f,t),!(t))"));
    }

    private static boolean parseBoolExpr(String expression) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == ')') {
                Set<Character> seen = new HashSet<>();
                while (!st.empty() && st.peek() != '(') {
                    seen.add(st.pop());
                }
                st.pop();
                char op = st.pop();
                if (op == '&') {
                    st.push(seen.contains('f') ? 'f' : 't');
                } else if (op == '|') {
                    st.push(seen.contains('t') ? 't' : 'f');
                } else {
                    st.push(seen.contains('t') ? 'f' : 't');
                }
            } else if (c != ',') {
                st.push(c);
            }
        }
        return st.pop() == 't';
    }
}
