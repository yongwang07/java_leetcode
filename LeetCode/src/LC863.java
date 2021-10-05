import java.util.*;

public class LC863 {
    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(5);
        root.left.right = new Node(2);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);
        root.left.left = new Node(6);
        root.right = new Node(1);
        root.right.left = new Node(0);
        root.right.right = new Node(8);
//        visit(root);
        distanceK(root, root.left, 2);
    }
    public static void visit(Node root) {
        if (root == null) return;
        System.out.print(root.value + ",");
        visit(root.left);
        visit(root.right);
    }
    static class Node {
        int value;
        Node left = null;
        Node right = null;
        public Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
        }
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
    public static void dfs(Node node, Map<Node, Node> parent) {
        if (node.left != null) {
            parent.put(node.left, node);
            dfs(node.left, parent);
        }
        if (node.right != null) {
            parent.put(node.right, node);
            dfs(node.right, parent);
        }
    }
    public static void distanceK(Node root, Node target, int k) {
        Map<Node, Node> parent = new HashMap<>();
        dfs(root, parent);
        Set<Node> visited = new HashSet<>();
        Queue<Node> q = new LinkedList<>();
        q.offer(target);
        visited.add(target);
        int dist = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                Node cur = q.poll();
                if (dist == k) {
                    System.out.println("value=" + cur.value);
                }
                if (cur.left != null && !visited.contains(cur.left)) {
                    q.offer(cur.left);
                    visited.add(cur.left);
                }
                if (cur.right != null && !visited.contains(cur.right)) {
                    q.offer(cur.right);
                    visited.add(cur.right);
                }
                Node p = parent.get(cur);
                if (p != null && !visited.contains(p)) {
                    q.offer(p);
                    visited.add(p);
                }
            }
            dist++;
            if (dist > k) break;
        }
    }
}
