import java.util.*;

public class LC721 {
    public static void main(String ...args) {
        System.out.println("HHHHH");
        String[][] accounts = {{"John", "johnsmith@mail.com", "john00@mail.com"},
                {"John", "johnsmith@mail.com", "john_newyork@mail.com"},
                {"Mary", "mary@mail.com"}};
        accountsMerge(accounts);
    }
    //Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
    // ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
    private static String[][] accountsMerge(String[][] accounts) {
        Map<String, List<Integer>> mails = new HashMap<>();
        for (int k = 0; k < accounts.length; k++) {
            String[] info = accounts[k];
            for (int i = 1; i < info.length; i++) {
                List<Integer> people = mails.getOrDefault(info[i], new ArrayList<>());
                people.add(k);
                mails.put(info[i], people);
            }
        }
        Set<Integer> visited = new HashSet<>();
        for (int k = 0; k < accounts.length; k++) {
            if (visited.contains(k)) continue;
            Queue<Integer> q = new LinkedList<>();
            q.offer(k);
            Set<Integer> all = new HashSet<>();
            while (!q.isEmpty()) {
                int p = q.poll();
                all.add(p);
                visited.add(p);
                for (int i = 1; i < accounts[p].length; i++) {
                    mails.get(accounts[p][i]);
                }
            }
        }
        //System.out.println(mails.toString());
        return null;
    }
}
