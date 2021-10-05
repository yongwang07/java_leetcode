import java.util.*;

public class LC460 {
    public static void main(String[] args) {
        System.out.println("LC460");
        LC460.put(1, 1);
        LC460.put(2, 2);
        System.out.println(LC460.get(1));
        LC460.put(3, 3);
        System.out.println(LC460.get(2));
        System.out.println(LC460.get(3));
        LC460.put(4, 4);
        System.out.println(LC460.get(1));
        System.out.println(LC460.get(3));
        System.out.println(LC460.get(4));
    }
    static class Node {
        int value;
        int freq;
        public Node(int value, int freq) {
            this.value = value;
            this.freq = freq;
        }
    }

    public static int minFreq;
    private static int capacity = 2;
    public static Map<Integer, Node> cache = new HashMap<>();
    public static Map<Integer, Set<Integer>> freqMap = new HashMap<>();

    public static void update(int key, Node node) {
        Set<Integer> keys = freqMap.get(node.freq);
        keys.remove(key);
        if (keys.size() == 0) {
            minFreq++;
        }
        node.freq++;
        freqMap.computeIfAbsent(node.freq, k -> new HashSet<>()).add(key);
    }
    public static void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node n = cache.get(key);
            n.value = value;
            update(key, n);
        } else {
            Node newNode = new Node(value, 1);
            cache.put(key, newNode);
            freqMap.computeIfAbsent(1, k -> new HashSet<>()).add(key);
            if (cache.size() > capacity) {
                Set<Integer> keys = freqMap.get(minFreq);
                Integer one = keys.iterator().next();
                keys.remove(one);
                cache.remove(one);
            }
            minFreq = 1;
        }
    }
    public static int get(int key) {
        if (cache.containsKey(key)) {
            Node n = cache.get(key);
            update(key, n);
            return n.value;
        }
        return -1;
    }
}
