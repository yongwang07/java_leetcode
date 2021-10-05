import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC1751 {
    public static void main(String[] args) {
//        maxValue(new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 1}}, 2);
//        maxValue(new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 10}}, 2);
//        maxValue(new int[][]{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4}}, 3);
        List<Node> nodes1 = Arrays.asList(new Node('-'), new Node('1'), new Node('2'), new Node('4'));
        List<Node> nodes2 = Arrays.asList(new Node('+'), new Node('1'));
        List<Node> nodes3 = Arrays.asList(new Node('+'), new Node('2'), new Node('5'));
        List<List<Node>> n = new ArrayList<>();
        n.add(new ArrayList<>(nodes1));
        n.add(new ArrayList<>(nodes2));
        n.add(new ArrayList<>(nodes3));
        sumOfNode(n);
    }
    public static void maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int[][] dp = new int[1 + events.length][k + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= events.length; j++) {
                int p = Arrays.binarySearch(events, 0, j - 1, new int[]{events[j - 1][0], events[j - 1][0], 0}, (a, b) -> a[1] - b[1]);
                p = p < 0 ? -p - 1 : p;
                dp[j][i] = Math.max(dp[j - 1][i], dp[p][i - 1] + events[j - 1][2]);
            }
        }
        System.out.println(dp[events.length][k]);
    }
    static class Node {
        char value;
        public Node(char value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
    public static List<Node> addTwoList(List<Node> a, List<Node> b) {
        System.out.println(a);
        System.out.println(b);
        int af = a.get(0).value == '-' ? -1 : 1;
        int bf = b.get(0).value == '-' ? -1 : 1;
        int i = a.size() - 1, j = b.size() - 1, carry = 0;
        int sign = bf == af ? 1 : -1;
        for (; i >= 1 || j >= 1; i--, j--) {
            int av = i >= 1 ? (a.get(i).value - '0') : 0;
            int bv = j >= 1 ? (b.get(j).value - '0') : 0;
            int sum = av * sign + bv + carry;
            if (sum > 0) {
                carry = sum / 10;
            } else if (sum < 0) {
                carry = -1;
                sum += 10;
            } else carry = 0;
            b.get(j).value = (char) (sum % 10 + '0');
        }
        if (carry != 0) {
            System.out.println("carry=" + carry);
            Node carryNode = new Node((char)(carry + '0'));
            b.add(1, carryNode);
        }
        if (b.get(1).value == '0') {
            b.remove(1);
        }
        System.out.println(b);
        System.out.println("++++++++++++");
        return b;
    }
    public static int checkBig(List<Node> a, List<Node> b) {
        if (a.size() != b.size()) return a.size() > b.size() ? 1 : -1;
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i).value > b.get(i).value) return 1;
            else if (a.get(i).value < b.get(i).value) return -1;
        }
        return 0;
    }
    public static void sumOfNode(List<List<Node>> nodes) {
        int idx = 1;
        List<Node> res = nodes.get(0);
        do {
            List<Node> a = nodes.get(idx);
            int flag = checkBig(res, a);
            res = flag < 1 ? addTwoList(res, a) : addTwoList(a, res);
            idx += 1;
        } while (idx < nodes.size());
    }
}
//  Tell me about a time when you had disagreement with you manager / Give me an example of when you did more than
//  what was required in any job experience
//  Tell me about a time when you needed to sacrifice short-term profit for company long-term value/Tell me about
//  a time when you received negative feedback from others and how you responded.
//  Tell me about a time that the timeline is tight. What’s the outcome of it, and what is the bad impact?
//  Tell me about a time that you received criticism or bad feedback.
//  Tell me about a time that you are assigned an unreachable task.
//  Do you have disagreements with your team members?
//  Do you have a time that you provided a feature which is not required by others, but the feature is necessary in your mind?
//  Tell me about a time that you solved a complicated problem with a simple solution?
//  Describe a time when you were in disagreement with your manager.
//  FU: Will you make a different decision? (If the situation happens again)
//  Describe a time you take a job beyond your responsibility. What is the outcome?
//  Tell me a time you made a hard decision.
//  FU: How did you persuade others about your decision?
//  Tell me about a time you worked on something that you do not have knowledge about?
//BQ: simple solution that solves complex problem, tight deadline
//BQ‍‌‌‍: time when disagree, make quick decisions
//- not able to meet the commitments
//        - Tell me a time that I strongly disagreed with sth and had to compromise
//        - a time that I suggested sth outside my responsibilities