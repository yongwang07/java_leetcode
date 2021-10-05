import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC445 {
    public static void main(String[] args) {
        //(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4) = 7 -> 8 -> 0 -> 7
        Node a = new Node(7);
        a.next = new Node(2);
        a.next.next = new Node(4);
        a.next.next.next = new Node(3);
        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(4);
        addTwoNumbers(b, a);
        a = new Node(9);
        b = new Node(9);
        addTwoNumbers(a, b);
        subarraySum(new int[]{1, 2, 3}, 3);
        boolean c = backspaceCompare("ab#c", "ad#c");
        System.out.println(c);
        c = backspaceCompare("ab##", "c#d#");
        System.out.println(c);
        c = backspaceCompare( "a#c", "b");
        System.out.println(c);
    }
    public static boolean backspaceCompare(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1, cnt1 = 0, cnt2 = 0;
        while (i >= 0 || j >= 0) {
            while (i >= 0 && (a.charAt(i) == '#' || cnt1 > 0)) {
                cnt1 += a.charAt(i) == '#' ? 1 : -1;
                i--;
            }
            while (j >= 0 && (b.charAt(j) == '#' || cnt2 > 0)) {
                cnt2 += b.charAt(j) == '#' ? 1 : -1;
                j--;
            }
            if ( i >= 0 && a.charAt(i) != '#' && j >= 0 && b.charAt(j) != '#') {
                if (a.charAt(i--) != b.charAt(j--)) return false;
            }
        }
        return i == j;
    }
    public static void subarraySum(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        int sum = 0, res = 0;
        m.put(0, 1);
        for (int n : nums) {
            sum += n;
            if (m.get(sum - k) != null) {
                res += m.get(sum - k);
            }
            m.put(sum, m.getOrDefault(sum, 0) + 1);
        }
        System.out.println(res);
    }
    public static void printList(Node head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.value);
            head = head.next;
        }
        System.out.println(list);
    }
    public static int listLen(Node head) {
        int res = 0;
        while (head != null) {
            res++;
            head = head.next;
        }
        return res;
    }
    static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }
    public static int helper(Node a, Node b, int diff) {
        if (a == null) return 0;
        int sum;
        if (diff > 0) {
            sum = helper(a.next, b, diff - 1) + a.value;
        } else {
            sum = helper(a.next, b.next, diff) + a.value + b.value;
        }
        a.value = sum % 10;
        return sum / 10;
    }
    public static void addTwoNumbers(Node a, Node b) {
        int aLen = listLen(a);
        int bLen = listLen(b);
        Node longHeader = aLen > bLen ? a : b;
        Node shortHead = aLen < bLen ? a : b;
        int carry = helper(longHeader, shortHead, Math.abs(aLen - bLen));
        if (carry != 0) {
            Node head = new Node(carry);
            head.next = longHeader;
            longHeader = head;
        }
        printList(longHeader);
    }

}
