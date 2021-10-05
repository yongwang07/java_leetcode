import java.util.*;

public class LC428 {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node node3 = new Node(3);
        node3.children.add(new Node(5));
        node3.children.add(new Node(6));
        root.children.add(node3);
        root.children.add(new Node(2));
        root.children.add(new Node(4));
        serialize(root);
        deserialize("");
        Binary bRoot = new Binary(2);
        bRoot.left = new Binary(1);
        bRoot.right = new Binary(4);
        bRoot.right.left = new Binary(3);
        bRoot.right.right = new Binary(5);
        serializeBinary(bRoot);
        deserializeBinary("");
        bstSerialize(bRoot);
        bstDeserialize("");
        sumLarge(bRoot);
    }
    static class Binary {
        int value;
        Binary left = null;
        Binary right = null;
        public Binary(int value) {
            this.value = value;
        }
    }
    static class Node {
        int value;
        List<Node> children;
        public Node(int value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }
    public static void dfs(Node node, StringBuilder sb) {
        sb.append(node.value + ",").append(node.children.size() + ",");
        for (Node n : node.children) {
            dfs(n, sb);
        }
    }
    public static void serialize(Node node) {
        StringBuilder sb = new StringBuilder();
        dfs(node, sb);
        System.out.println(sb.substring(0, sb.length() - 1));
    }
    public static Node helper(Queue<String> q) {
        int v = Integer.parseInt(q.poll());
        int n = Integer.parseInt(q.poll());
        Node node = new Node(v);
        for (int i = 0; i < n; i++) {
            node.children.add(helper(q));
        }
        return node;
    }
    public static void deserialize(String s) {
        s = "1,3,3,2,5,0,6,0,2,0,4,0";
        Queue<String> q = new LinkedList<>(Arrays.asList(s.split(",")));
        Node root = helper(q);
        System.out.println("++++++++++");
        serialize(root);
    }
    public static void binaryDfs(Binary node, StringBuilder sb) {
        if (node == null) {
            sb.append("#,");
            return;
        }
        sb.append(node.value + ",");
        binaryDfs(node.left, sb);
        binaryDfs(node.right, sb);
    }
    public static void serializeBinary(Binary root) {
        StringBuilder sb = new StringBuilder();
        binaryDfs(root, sb);
        System.out.println(sb.substring(0, sb.length() - 1));
    }
    public static Binary helper2(Queue<String> q) {
        String s = q.poll();
        if (s.equals("#")) return null;
        Binary n = new Binary(Integer.parseInt(s));
        n.left = helper2(q);
        n.right = helper2(q);
        return n;
    }
    public static void deserializeBinary(String s) {
        s = "2,1,#,#,4,3,#,#,5,#,#";
        Queue<String> q = new LinkedList<>(Arrays.asList(s.split(",")));
        Binary root = helper2(q);
        System.out.println("===========");
        serializeBinary(root);
//        int[] a = {2, 3, 5, 9, 18, 22};
//        int k = 1;
//        int left = 0, right = a.length - 1;
//        while (left <= right) {
//            int mid = (left + right) >> 1;
//            if (a[mid] > k) {
//                right = mid - 1;
//            } else {
//                left = mid + 1;
//            }
//        }
//        System.out.println(left + "," + right);
//        TreeSet<Integer> t = new TreeSet<>(Arrays.asList(2, 3, 5, 9, 18, 22));
//        System.out.println(t.floor(91));
    }

    public static void helper3(Binary node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.value + ",");
        helper3(node.left, sb);
        helper3(node.right, sb);
    }
    public static void bstSerialize(Binary root) {
        StringBuilder sb = new StringBuilder();
        helper3(root, sb);
        System.out.println(sb.substring(0, sb.length() - 1));
    }
    public static Binary bstDeserialize(Queue<String> q) {
        if (q.isEmpty()) return null;
        int v = Integer.parseInt(q.poll());
        Binary node = new Binary(v);
        Queue<String> left = new LinkedList<>();
        while (!q.isEmpty() && Integer.parseInt(q.peek()) < v) {
            left.add(q.poll());
        }
        node.left = bstDeserialize(left);
        node.right = bstDeserialize(q);
        return node;
    }
    public static void bstDeserialize(String s) {
        s = "2,1,4,3,5";
        Binary root = bstDeserialize(new LinkedList<>(Arrays.asList(s.split(","))));
        System.out.println("after=======");
        bstSerialize(root);
        selfMultiply(new int[]{1,2,3,4});
    }

    public static int helper4(Binary node, int pre) {
        if (node == null) return pre + 0;
        pre = helper4(node.right, pre);
        System.out.println(node.value + "=>" + pre);
        pre += node.value;
        return helper4(node.left, pre);
    }

    public static void sumLarge(Binary root) {
        helper4(root, 0);
    }
//    {1,2,3,4} => {24, 12, 8, 6}
    public static void selfMultiply(int [] arr) {
        int[] res = new int[arr.length];
        Arrays.fill(res, 1);
        int left = 1, right = 1;
        for (int i = 0; i < arr.length; i++) {
            res[i] *= left;
            left *= arr[i];
            res[arr.length - i - 1] *= right;
            right *= arr[arr.length - i - 1];
        }
        System.out.println(Arrays.toString(res));
    }
}
