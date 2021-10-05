import java.util.*;
import java.util.function.Function;

public class LC227 {
    public static void main(String[] args) {
//        calculate("(1+(4+5+2)-3)+(6+8)");
//        calculate(" -2-1 + 2 ");
//        calculate2("-1 + 1");
        int a = calculate2("2*(5+5*2)/3+(6/2+8)");
        System.out.println("2*(5+5*2)/3+(6/2+8)=" + a);
        a = calculate2("(2+6* 3+5- (3*14/7+2)*5)+3");
        System.out.println("(2+6* 3+5- (3*14/7+2)*5)+3=" + a);
        System.out.println(" (1+2)  =" + calculate2("(1+2) "));
        System.out.println("-1 + 2=" + calculate2("-1 + 2"));
//        Node root = new Node(1);
//        root.left = new Node(3);
//        root.right = new Node(2);
//        findClosestLeaf(root, 1);
//        Node root = new Node(1);
//        root.right = new Node(3);
//        root.left = new Node(2);
//        root.left.left = new Node(4);
////        root.left.right = new Node(10);
//        root.left.left.left = new Node(5);
//        root.left.left.left.left = new Node(6);
//        findClosestLeaf(root, 2);
    }
    @FunctionalInterface
    interface Function<One, Two> {
        Two apply(One one, Two two, Two three);
    }

    static Function<Character, Integer> cal = (op, a, b) -> {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        if (op == '*') return a * b;
        return a / b;
    };
    public static int calculate2(String s) {
        int num = 0, res = 0, a = 0;
        char op = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            } else if (c != '(') {
                if (c != '(') {
                    if (op == '+' || op == '-') {
                        res += a;
                        a = op == '+' ? num : -num;
                    } else {
                        a = cal.apply(op, a, num);
                    }
                }
                op = c;
                num = 0;
            } else {
                int count = 0;
                int j = i;
                for (; j < s.length(); j++) {
                    if (s.charAt(j) == '(') count++;
                    if (s.charAt(j) == ')') count--;
                    if (count == 0) break;
                }
                num = calculate2(s.substring(i + 1, j));
                i = j;
            }
        }
        res += cal.apply(op, a, num);
        return res;
    }
    static class Node {
        int value;
        Node left, right;
        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
    public static void calculate(String s) {
        int res = 0, num = 0;
        char op = '+';
        for (char c : s.toCharArray()) {
            if (c == ' ' || c == '(' || c == ')') continue;
            if (Character.isDigit(c)) {
                num = 10 * num + (c - '0');
            } else {
                res += op == '+' ? num : num * -1;
                num = 0;
                op = c;
            }
        }
        res += op == '+' ? num : num * -1;
        System.out.println("res=" + res);
    }
    public static void dfs(Node node, int k, List<Node> path, List<Node> res) {
        if (node == null) return;
        if (node.value == k) {
            res.addAll(new ArrayList<>(path));
            res.add(node);
            return;
        }
        path.add(node);
        dfs(node.left, k, path, res);
        dfs(node.right, k, path, res);
        path.remove(path.size() - 1);
    }
    public static void findClosestLeaf(Node root, int k) {
        List<Node> path = new ArrayList<>();
        List<Node> res = new ArrayList<>();
        dfs(root, k, path, res);
        Queue<Node> q = new LinkedList<>();
        Node node = res.remove(res.size() - 1);
        q.offer(node);
        Set<Node> visited = new HashSet<>();
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.left == null && cur.right == null) {
                System.out.println(">>>" + cur);
                break;
            }
            visited.add(cur);
            if (cur.left != null && !visited.contains(cur.left)) {
                q.offer(cur.left);
            }
            if (cur.right != null && !visited.contains(cur.right)) {
                q.offer(cur.right);
            }
            if (res.size() > 0 && !visited.contains(res.get(res.size() - 1))) {
                q.offer(res.remove(res.size() - 1));
            }
        }
    }
}
