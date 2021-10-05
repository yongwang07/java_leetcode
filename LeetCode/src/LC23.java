import java.util.*;

public class LC23 {
    public static void main(String... args) {
        ListNode heada = new ListNode(1);
        heada.next = new ListNode(4);
        heada.next.next = new ListNode(5);
        ListNode headb = new ListNode(1);
        headb.next = new ListNode(3);
        headb.next.next = new ListNode(4);
        ListNode headc = new ListNode(2);
        headc.next = new ListNode(6);
        mergeKLists(new ListNode[]{heada, headb, headc});
//        mergeKLists2(new ListNode[]{heada, headb, headc});
        findItinerary(new String[][]{{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}});
        findItinerary(new String[][]{{"JFK","SFO"}, {"JFK","ATL"}, {"SFO","ATL"}, {"ATL","JFK"}, {"ATL","SFO"}});
    }
    private static void dfs(String stop, Map<String, PriorityQueue<String>> m, List<String> res) {
        res.add(stop);
        if (m.get(stop) != null) {
            for (String nextStop : m.get(stop)) {
                m.get(stop).remove(nextStop);
                dfs(nextStop, m, res);
            }
        }
    }
    public static void findItinerary(String[][] tickets) {
        List<String> res = new ArrayList<>();
        Map<String, PriorityQueue<String>> m = new HashMap<>();
        for (String[] ticket : tickets) {
            m.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).add(ticket[1]);
        }
        dfs("JFK", m, res);
        System.out.println(res);
    }
    public static void printList(ListNode head) {
        List<Integer> res = new ArrayList<>();
        while (head != null) {
            res.add(head.value);
            head = head.next;
        }
        System.out.println(res);
    }
    static class ListNode {
        int value;
        ListNode next = null;
        public ListNode(int value) {
            this.value = value;
        }
    }
    public static ListNode merge2list(ListNode a, ListNode b){
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (a != null && b != null) {
            if (a.value > b.value) {
                tail.next = b;
                b = b.next;
            } else {
                tail.next = a;
                a = a.next;
            }
            tail = tail.next;
        }
        tail.next = a != null ? a : b;
        return head.next;
    }
    public static void mergeKLists2(ListNode[] lists) {
        int begin = 0, end = lists.length - 1;
        while (begin < end) {
            int mid = (begin + end - 1) / 2;
            for (int i = 0; i <= mid; i++) {
                lists[i] = merge2list(lists[i], lists[end - i]);
            }
            end = (begin + end) / 2;
        }
        printList(lists[0]);
    }
    public static void mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> q = new PriorityQueue<>((a, b) -> a.value - b.value);
        for (ListNode node : lists) {
            q.add(node);
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!q.isEmpty()) {
            ListNode cur = q.poll();
            tail.next = cur;
            tail = tail.next;
            if (cur.next != null) {
                q.offer(cur.next);
            }
        }
        printList(head.next);
    }
}
