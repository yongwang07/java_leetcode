import java.util.*;

public class LC165 {
    public static void main(String[] args) {
//        int a = compareVersion("1.0.1", "1");  //1
//        System.out.println("a=" + a);
//        a = compareVersion("7.5.2.4", "7.5.3"); //-1
//        System.out.println("a=" + a);
//        a = compareVersion("1.0.0.0", "1.0"); //0
//        System.out.println("a=" + a);

        RandomizedSet randomSet = new RandomizedSet();
        System.out.println(randomSet.insert(1));
        System.out.println(randomSet.remove(2));
        System.out.println(randomSet.insert(2));
        System.out.println(randomSet.getRandom());
        System.out.println(randomSet.remove(1));
        System.out.println(randomSet.insert(2));
        System.out.println(randomSet.getRandom());
        Map<Integer, Node> map = new HashMap<>();
        Node one = new Node(1);
        map.put(1, one);
        Node two = new Node(2);
        map.put(2, two);
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.value - b.value);
        q.offer(one);
        q.offer(two);
        System.out.println(q);
        q.remove(one);
        System.out.println(q);
    }
    static class Node {
        int value;
        public Node(int i) {
            this.value = i;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
    public static int compareVersion(String a, String b) {
        String[] as = a.split("\\.");
        String[] bs = b.split("\\.");
        for (int i = 0; i < Math.max(as.length, bs.length); i++) {
            int av = i < as.length ? Integer.parseInt(as[i]) : 0;
            int bv = i < bs.length ? Integer.parseInt(bs[i]) : 0;
            if (av != bv) return av < bv ? -1 : 1;
        }
        return 0;
    }
    static class RandomizedSet {
        List<Integer> arr = new ArrayList<>();
        Map<Integer, Integer> m = new HashMap<>();
        boolean insert(int val) {
            if (m.containsKey(val)) return false;
            arr.add(val);
            m.put(val, arr.size() - 1);
            return true;
        }
        boolean remove(int val) {
            if (!m.containsKey(val)) return false;
            int idx = m.get(val);
            int replace = arr.remove(arr.size() - 1);
            arr.set(idx, replace);
            m.put(replace, idx);
            m.remove(val);
            return true;
        }
        int getRandom() {
            int idx = (int)(Math.random() * arr.size());
            return arr.get(idx);
        }
    }
}
