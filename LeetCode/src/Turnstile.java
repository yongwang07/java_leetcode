import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

import java.util.*;

public class Turnstile {
    public static void main(String[] args) {
//        getTimes(new int[]{0, 0, 1, 5}, new int[]{0, 1, 1, 0}); //{2, 0, 1, 5}
//        getTimes(new int[]{1, 2, 4}, new int[]{0, 1, 1}); //{1, 2, 4}
//        getTimes(new int[]{1, 1}, new int[]{1, 1}); //{1, 2}
//        getTimes(new int[]{1, 1, 3, 3, 4, 5, 6, 7, 7}, new int[]{1, 1, 0, 0, 0, 1, 1, 1, 1}); //{1, 2, 3, 4, 5, 6, 7, 8, 9}
//        findLeastNumOfUniqueInts(new int[]{5, 5, 4}, 1); // 1
//        findLeastNumOfUniqueInts(new int[]{4, 3, 1, 1, 3, 3, 2}, 3); // 2
//        numPairsDivisibleBy60(new int[]{30, 20, 150, 100, 40}); // 3
//        numPairsDivisibleBy60(new int[]{60, 60, 60}); // 3
//        countMinReversals("}{"); // 2
//        countMinReversals("{{{"); // -1
//        countMinReversals("{{{{"); // 2
//        countMinReversals("{{{{}}"); // 1
//        countMinReversals("}{{}}{{{"); // 3
//        reorderLogFiles(new String[]{"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"});
        //["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
//        System.out.println(pthFactor(10, 3)); // 5
//        corpFlightBookings(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5); //[10,55,45,25,25]
//        corpFlightBookings(new int[][]{{1, 2, 10}, {2, 2, 15}}, 2); //[10,25]
//        continuous(new int[]{1, 2, 3, 4, 5});
//        continuous(new int[]{2, 3, 5, 1});
//        continuous(new int[]{2, 2, 2, 2, 3, 2});
//        continuous(new int[]{5, 6, 5, 4, 3, 2, 1});
//        decodeString("mnesi___ya__k____mime",  3);
//        findPair("))?)?)?))?())()(??(?((())(?)?())((((()(?");
//        numWaysSplitParen("))?)?)?))?())()(??(?((())(?)?())((((()(?");
//        findPair("(()())(()[][))(?)(");
//        findPair("[()]??");
//        getMaximumMeetings(new int[]{1, 3, 3, 5, 7}, new int[]{2, 2, 1, 2, 1}); //4
//        getMaximumMeetings(new int[]{1, 3, 5}, new int[]{2, 2, 2}); //3
//        countAnalogousArrays(new int[]{-2, -1, -2, 5}, 3, 10); //3
//        splitArray(new int[]{10, 4, -8, 7}); //2
//        minPriority(new int[]{1, 4, 8, 4}); //[1, 2, 3, 2]
//        findMinimumCostToConnectServers(new String[][]{{"A", "B" , "1"},
//                {"B", "C", "4"},
//                {"B", "D", "6"},
//                {"D", "E", "5"},
//                {"C", "E", "1"}}, 5);
//        subarraysWithKDistinct("pqpqs", 2); //7
//        subarraysWithKDistinct("aabab", 3); //0
//        findMax(new int[]{62, 64, 77, 75, 71, 60, 79, 75}, 4);//  64
//        findVehicles(new int[]{6, 3, 2}); // [2, 0, 1]
////        cutOffRank(4,5, new int[]{2, 2, 3, 4, 5});
//        System.out.println(findSmallestDivisor("bcdbcdbcdbcd", "bcdbcd"));
//        System.out.println(findSmallestDivisor("bcdbcdbcd", "bcdbcd"));
//        numOfOtions(new int[]{2, 3}, new int[]{4}, new int[]{2, 3}, new int[]{1, 2}, 10); // 4
//        itemInContainer("**|**|*|***", new int[][]{{0, 1}, {0, 4}});
//        lruCacheMiss(new int[]{1,2,1,3,1,2}, 2);
//        uniquePairs(new int[]{1, 1, 2, 45, 46, 46}, 47);
//        uniquePairs(new int[]{1, 1, 1}, 2);
//        uniquePairs(new int[]{1, 5, 1, 5, 3, 3, 3, 2, 2, 4, 4}, 6);
//        numberOfSwapsToSort(new int[]{5, 4, 1, 2}); //5
//        numberOfSwapsToSort(new int[]{5, 2, 6, 1});
//        movieDuration(new int[]{1, 10, 25, 35, 60, 25}, 90);
//        movieDuration(new int[]{90, 85, 75, 60, 120, 150, 125}, 250); // 215
//        countGroups(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
//        countGroups(new int[][]{{1, 1, 0, 0}, {1, 1, 0, 1}, {0, 0, 1, 0}, {0, 0, 0, 1}});
//        minContainerSize(new int[]{10, 2, 20, 5, 15, 10, 1},3); // 31
//        minContainerSize(new int[]{10, 2, 20, 5, 15, 10, 1},5); // 43
        treasureIsland(new char[][]{{'O', 'O', 'O', 'O'}, {'D', 'O', 'D', 'O'}, {'O', 'O', 'O', 'O'},
                {'X', 'D', 'D', 'O'}});
//        numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3);
//        maximumUnits(new int[][]{{1,3},{2,2},{3,1}}, 4); // 8
//        maximumUnits(new int[][]{{5,10},{2,5},{4,7},{3,9}}, 10); // 91
//        //optimalUtilization(new int[][]{{1, 2}, {2, 4}, {3, 6}}, new int[][]{{1, 2}}, 7);
//        optimalUtilization(new int[][]{{1, 3}, {2, 5}, {3, 7}, {4, 10}},
//                new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}}, 10);
//        optimalUtilization(new int[][]{{1, 8}, {2, 15}, {3, 9}},
//                new int[][]{{1, 8}, {2, 11}, {3, 12}}, 20);
//        Map<String, List<String>> users = new HashMap<>();
//        users.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
//        users.put("Emma", Arrays.asList("song5", "song6", "song7"));
//        Map<String, List<String>> gen = new HashMap<>();
//        gen.put("Rock", Arrays.asList("song1", "song3"));
//        gen.put("Dubstep", Arrays.asList("song7"));
//        gen.put("Techno", Arrays.asList("song2", "song4"));
//        gen.put("Pop", Arrays.asList("song5", "song6"));
//        gen.put("Jazz", Arrays.asList("song8", "song9"));
//        favoritegenre(users, gen);
    }
    public static void getTimes(int[] times, int[] directions) {
        Queue<Integer> entry = new LinkedList<>();
        Queue<Integer> exit = new LinkedList<>();
        for (int i = 0; i < times.length; i++) {
            boolean a  = directions[i] == 0 ? entry.add(i) : exit.add(i);
        }
        int preDir = -1, curTime = 0;
        int[] res = new int[times.length];
        while (!entry.isEmpty() && !exit.isEmpty()) {
            int curEntry = entry.peek();
            int curExit = exit.peek();
            int curEnterTime = Math.max(times[curEntry], curTime);
            int curExitTime = Math.max(times[curExit], curTime);
            if (curEnterTime < curExitTime) {
                res[curEntry] = curEnterTime;
                preDir = 0;
                entry.poll();
                curTime = curEnterTime + 1;
            } else if (curExitTime < curEnterTime) {
                res[curExit] = curExitTime;
                preDir = 1;
                exit.poll();
                curTime = curExitTime + 1;
            } else {
                if (preDir == 0) {
                    res[curEntry] = curEnterTime;
                    entry.poll();
                    curTime = curEnterTime + 1;
                } else {
                    res[curExit] = curExitTime;
                    exit.poll();
                    curTime = curExitTime + 1;
                }
            }
        }
        while (!entry.isEmpty() || !exit.isEmpty()) {
            int cur = !entry.isEmpty() ? entry.poll() : exit.poll();
            int time = Math.max(curTime, times[cur]);
            res[cur] = time;
            curTime = time + 1;
        }
        System.out.println(Arrays.toString(res));
    }

    public static void findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int a : arr) {
            cntMap.put(a, cntMap.getOrDefault(a, 0) + 1);
        }
        PriorityQueue<Integer> q = new PriorityQueue<>(cntMap.values());
        while (k > 0) {
            int top = q.peek();
            if (top <= k) {
                q.poll();
            }
            k -= top;
        }
        System.out.println(q.size());
    }

    public static void numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int t : time) {
            int total = map.getOrDefault((60 - t % 60), 0);
            res += total;
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        System.out.println(res);
    }

    public static void countMinReversals(String expr) {
        int res = 0;
        Stack<Character> st = new Stack<>();
        for (char c : expr.toCharArray()) {
            if (c == '{') {
                st.push(c);
            } else {
                if (!st.isEmpty() && st.peek() == '{') {
                    st.pop();
                } else {
                    res++;
                    st.push('{');
                }
            }
        }
        res = st.size() % 2 == 1 ? -1 : res + st.size() / 2;
        System.out.println(res);
    }
    public static void reorderLogFiles(String[] logs) {
        List<String> digitLogs = new ArrayList<>();
        List<String[]> textLogs = new ArrayList<>();
        for (String log : logs) {
            int pos = log.indexOf(" ");
            String id = log.substring(0, pos);
            String context = log.substring(pos + 1);
            if (Character.isDigit(context.charAt(0))) {
                digitLogs.add(log);
            } else {
                textLogs.add(new String[]{id, context});
            }
        }
        Collections.sort(textLogs, (a, b) -> a[1].equals(b[1]) ? a[0].compareTo(b[0]) : a[1].compareTo(b[1]));
        List<String> res = new ArrayList<>();
        for (String[] log : textLogs) {
            res.add(log[0] + " " + log[1]);
        }
        for (String log : digitLogs) {
            res.add(log);
        }
        System.out.println(res);
    }

    public static int pthFactor(int n, int p) {
        List<Integer> big = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                p --;
                if (p == 0) {
                    return i;
                }
                big.add(n / i);
            }
        }
        return big.get(big.size() - p);
    }
//    给你一个array of integer，以及一个最大差值， k，要你求最少能分成多少个subset，并且每个subset中数据的最大差值不大于k
//    arr = [1, 4, 8, 7]. k = 3   output: 2
    public static void corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] book : bookings) {
            res[book[0] - 1] += book[2];
            if (book[1] < n) {
                res[book[1]] -= book[2];
            }
        }
        for (int i = 1; i < n; i++) {
            res[i] += res[i - 1];
        }
        System.out.println(Arrays.toString(res));
    }
    public static void continuous(int[] arr) {
        int res = 1, pre = arr[0], maxLen = 0;
        char type = '?';
        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            if (cur == pre) {
                type = '=';
                res++;
                maxLen = Math.max(maxLen, res);
            } else {
                char newType = cur > pre ? '>' : '<';
                if ((newType == '<' && type == '>') || (newType == '>' && type == '<')) {
                    res = 1;
                } else {
                    res++;
                    maxLen = Math.max(maxLen, res);
                }
                pre = cur;
                type = newType;
            }
        }
        System.out.println(maxLen);
    }
    public static void decodeString(String str, int N) {
        int colLen = str.length() / N;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < colLen; i++) {
            int row = 0, col = i;
            while (row < N && col < colLen) {
                char c = str.charAt(row * colLen + col);
                sb.append(c == '_' ? ' ' : c);
                row++;
                col++;
            }
        }
        System.out.println(sb);
    }
    public static boolean isBalance(int a, int b, int c) {
//        System.out.println(a + "," + b + "," + c);
        long remain = Math.abs(a) + Math.abs(b);
        if (remain > 0) return remain == c;
        return c % 2 == 0;

    }
    public static void findPair(String st) {
        System.out.println(st);
        int res = 0, a = 0, b = 0, c = 0;
        for (char cur : st.toCharArray()) {
            if (cur == '(') a++;
            else if (cur == ')') a--;
            else if (cur == '[') b++;
            else if (cur == ']') b--;
            else c++;
            if (isBalance(a, b, c)) {
                res++;
            }
        }
        res = isBalance(a, b, c) ? res - 1 : 0;
        System.out.println(">>" + res);
    }

    public static boolean isBalance(String s)
    {
        int ro=0,rc=0,so=0,sc=0,ques=0;
        for(char i: s.toCharArray())
        {
            if(i=='(')
                ro++;
            else if(i==')')
                rc++;
            else if(i==')')
                rc++;
            else if(i=='[')
                so++;
            else if(i==']')
                sc++;
            else if(i=='?')
                ques++;
        }

        int diff= Math.abs(ro-rc) + Math.abs(so-sc);

        if(diff==0 && ques%2==0) {
            return true;
        }
        return diff - ques == 0;
    }

    public static void getMaximumMeetings(int[] start, int[] timeTaken) {
        List<int[]> meetings = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            meetings.add(new int[]{start[i], start[i] + timeTaken[i]});
        }
        Collections.sort(meetings, (a, b) -> a[1] - b[1]);
        int res = 1;
        int endTime = meetings.get(0)[1];
        for (int i = 1; i < meetings.size(); i++) {
            int curEndTime = meetings.get(i)[1];
            int curStartTime = meetings.get(i)[0];
            if (curStartTime >= endTime) {
                endTime = Math.max(endTime, curEndTime);
                res++;
            }
        }
        System.out.println(res);
    }
    public static void countAnalogousArrays(int[] consecutiveDifference, int lowerBound, int upperBound) {
        int pre = consecutiveDifference[consecutiveDifference.length - 1];
        int min = pre, max = pre;
        for (int i = consecutiveDifference.length - 2; i >=0; i--) {
            int cur = consecutiveDifference[i] + pre;
            max = Math.max(cur, max);
            min = Math.min(min, cur);
            pre = cur;
        }
        int left = Math.max(lowerBound - max, lowerBound - min);
        int right = Math.min(upperBound - max, upperBound - min);
        int res = right - left + 1;
        System.out.println(res);
    }

    public static void splitArray(int[] arr) {
        int[] prefixSum = new int[arr.length];
        prefixSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            if (prefixSum[i - 1] > (prefixSum[prefixSum.length - 1] - prefixSum[i - 1])) {
                res++;
            }
        }
        System.out.println(res);
    }
    public static void minPriority(int[] arr) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
        int priority = 1;
        int[] res = new int[arr.length];
        for (List<Integer> values : map.values()) {
            for (int index : values) {
                res[index] = priority;
            }
            priority++;
        }
        System.out.println(Arrays.toString(res));
    }
    public static class Node {
        String node;
        int idx;
        int cost;
        public Node(String node, int idx, int cost) {
            this.node = node;
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static void findMinimumCostToConnectServers(String[][] connections, int N) {
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        Map<String, List<Node>> graph = new HashMap<>();
        for (int i = 0; i < connections.length; i++) {
            String start = connections[i][0];
            String end = connections[i][1];
            int cost = Integer.parseInt(connections[i][2]);
            graph.computeIfAbsent(start, k -> new ArrayList<>()).add(new Node(end, i, cost));
            graph.computeIfAbsent(end, k -> new ArrayList<>()).add(new Node(start, i, cost));
        }
        Set<String> visited = new HashSet<>();
        List<Node> res = new ArrayList<>();
        q.offer(new Node("A", -1, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (visited.contains(node.node)) {
                continue;
            }
            visited.add(node.node);
            res.add(node);
            for (Node nextNode : graph.get(node.node)) {
                if (!visited.contains(nextNode.node)) {
                    q.offer(nextNode);
                }
            }
        }
        //[[A,B,1], [B,C,4], [C,E,1], [D,E,5]]
        res.stream().filter(n -> n.idx != -1).forEach(n -> System.out.println(Arrays.toString(connections[n.idx])));
    }
    public static void subarraysWithKDistinct(String A, int K) {
        System.out.println(helper(A, K) - helper(A, K - 1));
    }
    public static int helper(String A, int K) {
        int res = 0, left = 0;
        Map<Character, Integer> m = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            m.put(A.charAt(i), m.getOrDefault(A.charAt(i), 0) + 1);
            while (m.size() > K) {
                char c = A.charAt(left);
                int count = m.get(c);
                if (count == 1) {
                    m.remove(c);
                } else {
                    m.put(c, count - 1);
                }
                left++;
            }
            res += i - left + 1;
        }
        return res;
    }

    public static void findMax(int[] arr, int k) {
        LinkedList<Integer> st = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            while (st.size() > 0 && (i - st.getFirst() > k - 1)) {
                st.removeFirst();
            }
            while (st.size() > 0 && arr[st.getLast()] > arr[i]) {
                st.pollLast();
            }
            st.addLast(i);
            if (i >= k - 1) {
                max = Math.max(max, arr[st.peekFirst()]);
            }
        }
        System.out.println(max);
    }

    public static void findVehicles(int[] vehicles) {
        int[] res = new int[vehicles.length];
        int i = 0;
        for (int vehicle : vehicles) {
            if (vehicle % 2 == 1) {
                res[i++] = 0;
            } else {
                int count = 1;
                while (vehicle / 4 > 0) {
                    vehicle = vehicle / 4;
                    count++;
                }
                res[i++] = count;
            }
        }
        System.out.println(Arrays.toString(res));
    }

    public static void cutOffRank(int cutOffRank, int num, int[] scores) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int score : scores) {
            map.put(score, map.getOrDefault(score, 0) + 1);
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            res += count;
            cutOffRank -= count;
            if (count < 0) break;
        }
        System.out.println(res);
    }
    public static int findSmallestDivisor(String s, String t) {
        if (s.length() < t.length()) return -1;

        int ans = t.length();
        String res = s.replaceAll(t, "");
        if (res.length() != 0) return -1;

        for (int i = t.length(); i >= 0; i--) {
            String sub = t.substring(0, i);
            if (i > 0 && s.length() % i != 0) continue;
            String rep = s.replaceAll(sub, "");
            if (rep.length() == 0) {
                ans = Math.min(ans, sub.length());
            }
        }
        return ans;
    }
    public static void numOfOtions(int[] jeans, int[] top, int[] skirt, int[] shoes, int budget) {
        int count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        if(budget == 0) {
            System.out.println(count);
        }
        for(int i1: jeans) {
            for(int i2: top) {
                int sum = i1 + i2;
                map.put(sum, map.getOrDefault(sum,0) + 1);
            }
        }

        for (int i3 : skirt) {
            for (int i4 : shoes) {
                int sum = budget - (i3 + i4);
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getKey() <= sum) {
                        count += entry.getValue();
                    }
                }
            }
        }
        System.out.println(count);
    }

    public static void itemInContainer(String str, int[][] ranges) {
        System.out.println(str);
        int pre = -1;
        int count = 0;
        int[] res = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '*') continue;
            if (pre == -1) {
                pre = i;
                continue;
            }
            count += i - pre - 1;
            res[i] = count;
            pre = i;
        }
        pre = -1;
        int[] pres = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '|') {
                pres[i] = i;
                pre = i;
            } else {
                pres[i] = pre;
            }
        }
        int after = -1;
        int[] afters = new int[str.length()];
        for (int i = str.length() - 1; i >=0; i--) {
            char c = str.charAt(i);
            if (c == '|') {
                afters[i] = i;
                after = i;
            } else {
                afters[i] = after;
            }
        }
        int[] d = new int[ranges.length];
        int q = 0;
        for (int[] range :ranges) {
            int low = afters[range[0]];
            int high = pres[range[1]];
            d[q++] = low == -1 || high == -1 ? 0 : res[high] - res[low];
        }
        System.out.println(Arrays.toString(d));

        int[] a = {4, 6, 6, 7}, b = {6, 8, 9};
        int x = 0, y = 0, w = 0;
        while (x < a.length && y < b.length) {
            if (a[x] > b[y]) {
                y++;
            } else {
                w += b[y] - a[x];
                x++;
            }
        }
        System.out.println("w=" + w);
    }

    public static void movieDuration(int[] movieDuration, int flightDuration) {
        int target = flightDuration - 30;
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < movieDuration.length; i++) {
            int duration = movieDuration[i];
            if (duration >= target) continue;
            m.computeIfAbsent(duration, k -> new ArrayList<>()).add(i);
        }
        int left = 1, right = target - 1;
        List<List<Integer>> res = new ArrayList<>();
        int max = 0;
        while (left < right) {
            if (m.get(left) == null) {
                left++;
                continue;
            }
            if (m.get(right) == null) {
                right--;
                continue;
            }
            int sum = left + right;
            if (sum > target) {
                right--;
                continue;
            }
            if (sum > max) {
                res.clear();
            }
            res.add(m.get(left));
            res.add(m.get(right));
            max = sum;
            left++;
        }
        System.out.println(max +"," + res);
    }
    public static void lruCacheMiss(int[] visit, int capacity) {
        LinkedHashSet<Integer> hash = new LinkedHashSet<>();
        int miss = 0;
        for (int page : visit) {
            if (hash.contains(page)) {
                hash.remove(page);
                hash.add(page);
            } else {
                if (hash.size() >= capacity) {
                    Integer first = hash.iterator().next();
                    hash.remove(first);
                }
                hash.add(page);
                miss++;
            }
        }
        System.out.println(miss);
    }

    public static int droppedRequest(List<Integer> requestTime) {
        int ans = 0;
        for (int i = 0; i < requestTime.size(); i++) {
            if (i > 2 && requestTime.get(i).longValue() == requestTime.get(i - 3).longValue()) {
                ans++;
            } else if (i > 19 && (requestTime.get(i).longValue() - requestTime.get(i - 20).longValue() < 10)) {
                ans++;
            } else if (i > 59 && (requestTime.get(i).longValue() - requestTime.get(i - 60).longValue() < 60)) {
                ans++;
            }
        }
        return ans;
    }

    public static void uniquePairs(int[] nums, int target) {
        Set<Integer> st = new HashSet<>();
        int res = 0;
        boolean same = false;
        for (int num : nums) {
            if (num * 2 == target) {
                if (!same && st.contains(num)) {
                    res++;
                    same = true;
                }
            } else if (st.contains(target - num)) {
                if (!st.contains(num)) {
                    res++;
                }
            }
            st.add(num);
        }
        System.out.println(res);
    }
    public static void numberOfSwapsToSort(int[] arr) {
//        Stack<Integer> st = new Stack<>();
//        boolean swap = true;
//        while (swap) {
//            st.clear();
//            swap = false;
//            for (int i = 0; i < arr.length; i++) {
//                if (!st.isEmpty() && arr[st.peek()] > arr[i]) {
//                    int j = st.peek();
//                    int temp = arr[j];
//                    arr[j] = arr[i];
//                    arr[i] = temp;
//                    swap = true;
//                    System.out.println(Arrays.toString(arr));
//                    break;
//                } else {
//                    st.push(i);
//                }
//            }
//        }
        List<Integer> t = new ArrayList<>();
        int[] res = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
//            int left = 0, right = t.size();
//            while (left < right) {
//                int mid = left + (right - left) / 2;
//                if (t.get(mid).longValue() >= arr[i]) right = mid;
//                else left = mid + 1;
//            }
            int right = Collections.binarySearch(t, arr[i]);
            right = right >= 0 ? right : -right - 1;
            res[i] = right;
            t.add(right, arr[i]);
        }
        System.out.println(Arrays.toString(res));
    }

    public static void dfs(int[][] relatives, int i, Set<Integer> visited) {
        visited.add(i);
        for (int j = 0; j < relatives[0].length; j++) {
            if (relatives[i][j] == 0 || visited.contains(j)) continue;
            dfs(relatives, j, visited);
        }
    }
    public static void countGroups(int[][] relatives) {
        int res = 0, m = relatives.length;
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < m; i++) {
            if (visited.contains(i)) continue;
            dfs(relatives, i, visited);
            res++;
        }
        System.out.println(res);
    }

    public static void minContainerSize(int[] arr, int K) {
//        int[][] dp = new int[K + 1][arr.length + 1];
//        Arrays.stream(dp).forEach(row -> Arrays.fill(row, 100));
//        dp[0][0] = 0;
//        for (int i = 1; i <= arr.length; i++) {
//            for (int day = 1; day <= K; day++) {
//                int max = 0;
//                for (int j = i - 1; j >= day - 1; j--) {
//                    max = Math.max(max, arr[j]);
//                    dp[day][i] = Math.min(dp[day][i], dp[day - 1][j] + max);
//                }
//            }
//        }
//        for (int[] d : dp) System.out.println(Arrays.toString(d));
        int[][] dp = new int[K][arr.length];
        Arrays.stream(dp).forEach(row -> Arrays.fill(row, 100));
        dp[0][0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            for (int day = 0; day < K; day++) {
                int max = day == 0 ? dp[day][i - 1] : 0;
                for (int w = i; w >= 1; w--) {
                    max = Math.max(arr[w], max);
                    dp[day][i] = Math.min(dp[day][i], (day == 0 ? 0 : dp[day - 1][w - 1]) + max);
                }
            }
        }
        System.out.println(dp[K - 1][arr.length - 1]);
    }

    public static int treasureIsland(char[][] island) {
        int m = island.length, n = island[0].length;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(new int[]{0, 0});
        int level = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] cur = q.poll();
                int x = cur[0], y = cur[1];
                visited.add(x * m + y);
                if (x == m - 1 && y == 0) {
                    System.out.println(level);
                    minStringLen("aaacbaa");
                    return level;
                }
                for (int[] dir : dirs) {
                    int row = x + dir[0], col = y + dir[1];
                    if (row < 0 || row > m - 1 || col < 0 || col > n - 1) continue;
                    if (island[row][col] == 'D' || visited.contains(row * m + col)) continue;
                    System.out.println(level + "," + row + "," + col);
                    q.offer(new int[]{row, col});
                }
            }
            level++;
        }
        return -1;
    }
    public static void minStringLen(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            char a = str.charAt(left);
            char b = str.charAt(right);
            if (a != b) break;
            while (left <= right && str.charAt(left) == a) left++;
            while (right >= left && str.charAt(right) == a) right--;
        }
        int res = left > right ? 0 : right - left + 1;
        System.out.println(res);
    }

    public static void numSubarrayBoundedMax(int[] A, int L, int R) {
        int count = 0;
        int start = -1;
        int last = -1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > R) {
                start = last = i;
                continue;
            }
            if (A[i] >= L) {
                last = i;
            }
            count += last - start;
        }
        System.out.println(count);
    }
    public static void maximumUnits(int[][] boxTypes, int truckSize) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        q.addAll(Arrays.asList(boxTypes));
        int res = 0;
        while (!q.isEmpty() && truckSize > 0) {
            int[] cur = q.poll();
            int take = Math.min(cur[0], truckSize);
            res += take * cur[1];
            truckSize -= take;
        }
        System.out.println(res);
    }

    public static void optimalUtilization(int[][] a, int[][] b, int target) {
        Arrays.sort(a, (m, n) -> m[1] - n[1]);
        Arrays.sort(b, (m, n) -> m[1] - n[1]);
        int big = a.length - 1;
        int small = 0;
        int max = 0;
        List<int[]> res = new ArrayList<>();
        while (big >= 0 && small < b.length) {
            int sum = a[big][1] + b[small][1];
            if (sum > target) {
                big--;
            } else if (sum > max) {
                max = sum;
                res.clear();
                res.add(new int[]{a[big][0], b[small][0]});
                small++;
            } else if (sum == max) {
                res.add(new int[]{a[big][0], b[small][0]});
                small++;
            } else {
                small++;
            }
        }
        res.stream().forEach(pair -> System.out.println(Arrays.toString(pair)));
    }

    public static void favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
        Map<String, String> songType = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : genreMap.entrySet()) {
            for (String song : entry.getValue()) {
                songType.put(song, entry.getKey());
            }
        }
        Map<String, List<String>> userTypes = new HashMap<>();
        for (Map.Entry<String, List<String>> user : userMap.entrySet()) {
            List<String> res = new ArrayList<>();
            int max = 0;
            Map<String, Integer> typeCnt = new HashMap<>();
            for (String song : user.getValue()) {
                String type = songType.get(song);
                int count = typeCnt.getOrDefault(type, 0) + 1;
                typeCnt.put(type, count);
                if (count > max) {
                    max = count;
                    res.clear();
                    res.add(type);
                } else if (count == max) {
                    res.add(type);
                }
            }
            userTypes.put(user.getKey(), res);
        }
        System.out.println(userTypes);
    }

}