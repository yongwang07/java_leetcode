import java.util.*;

public class LC253 {
    public static void main(String[] args) {
        minMeetingRooms(new int[][]{{0, 30}, {5, 14}, {14, 20}, {5, 10}});
        minMeetingRooms(new int[][]{{7,10}, {2,4}});
    }
    public static void minMeetingRooms(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Map<Integer, List<Integer>> startsMap = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            List<Integer> starts = startsMap.getOrDefault(interval[0], new ArrayList<>());
            starts.add(i);
            startsMap.put(interval[0], starts);
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }
        int rooms = 0, res = 0;
        for(Map.Entry<Integer, Integer> item : map.entrySet()) {
            rooms += item.getValue();
            List<Integer> assigns = startsMap.get(item.getKey());
            if (assigns != null) {
                int k = rooms;
                for (int assign : assigns) {
                    System.out.println(assign + " -> " + k--);
                }
            }
            res = Math.max(res, rooms);
        }
        System.out.println("res=" + res);
    }
}
