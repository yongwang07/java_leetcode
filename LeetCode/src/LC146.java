import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LC146 {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        int a = cache.get(1); // returns 1
        System.out.println(a);
        cache.put(3, 3);    // evicts key 2
        a = cache.get(2);   // returns -1 (not found)
        System.out.println(a);
        cache.put(4, 4);    // evicts key 1
        a = cache.get(1);       // returns -1 (not found)
        System.out.println(a);
        a = cache.get(3);       // returns 3
        System.out.println(a);
        a = cache.get(4);       // returns 4
        System.out.println(a);
    }
    static class Node {
        int value;
        int key;
        Node pre;
        Node next;
        public Node(int key, int value) {
            this.value = value;
            this.key = key;
        }
    }
    static class DoubleLinkedList {
        private Node head;
        private Node tail;
        private int size;
        public DoubleLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.pre = head;
        }
        public void append(Node node) {
            node.pre = tail.pre;
            node.next = tail;
            tail.pre.next = node;
            tail.pre = node;
            size++;
        }
        public void remove(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size--;
        }
        public Node pollFirst() {
            Node d = head.next;
            head.next = d.next;
            d.next.pre = head;
            size--;
            return d;
        }
        public int size() {
            return size;
        }
    }
    static class LRUCache {
        private int capacity;
        Map<Integer, Node> m = new HashMap<>();
        DoubleLinkedList nodes = new DoubleLinkedList();

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        void put(int key, int value) {
            if (m.containsKey(key)) {
                Node n = m.get(key);
                n.value = value;
                nodes.remove(n);
                nodes.append(n);
            } else {
                Node node = new Node(key, value);
                nodes.append(node);
                m.put(key, node);
                if (nodes.size() > capacity) {
                    Node n = nodes.pollFirst();
                    m.remove(n.key);
                }
            }
        }

        int get(int key) {
            if (m.containsKey(key)) {
                Node n = m.get(key);
                nodes.remove(n);
                nodes.append(n);
                return m.get(key).value;
            }
            return -1;
        }
    }
}

