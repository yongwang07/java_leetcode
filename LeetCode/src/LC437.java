import java.util.HashMap;
import java.util.Map;

public class LC437 {
    public static class Node {
        Node left;
        Node right;
        int value;
        public Node(int value) {
            this.value = value;
        }
    }
//                   10
//                  /  \
//                  5   -3
//                  / \    \
//                  3   2   11
//                  / \   \
//                  3  -2   1
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(-3);
        root.left.left = new Node(3);
        root.left.right = new Node(2);
        root.left.left.left = new Node(3);
        root.left.left.right = new Node(-2);
        root.left.right = new Node(2);
        root.left.right.right = new Node(1);
        root.right = new Node(-3);
        root.right.right = new Node(11);
        pathSum(root, 8);
    }
    public static void visit(Node root) {
        if (root == null) return;
        System.out.print("," + root.value);
        visit(root.left);
        visit(root.right);
    }
    public static int res = 0;
    public static void pathSum(Node root, int sum) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 1);
        helper(root, sum, 0, m);
        System.out.println("res=" + res);
    }
    public static void helper(Node node, int sum, int curSum, Map<Integer, Integer> m) {
        if (node == null) return;
        int cur = curSum + node.value;
        if (m.getOrDefault(cur - sum, 0) > 0) res++;
        m.put(cur, m.getOrDefault(cur, 0) + 1);
        helper(node.left, sum, cur, m);
        helper(node.right, sum, cur, m);
        m.put(cur, m.get(cur) - 1);
    }
}
