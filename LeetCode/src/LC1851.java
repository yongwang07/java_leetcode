import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class LC1851 {
    public static void main(String[] args) {
        System.out.println("LC1851");
        minInterval(new int[][]{{1, 4}, {2, 4}, {3, 6}, {4, 4}}, new int[]{2, 3, 4, 5}); //[3,3,1,4]
    }
    public static void minInterval(int[][] intervals, int[] queries) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> p1[1] - p1[0] - p2[1] + p2[0]);//按区间长度排序的最小堆
        int n = intervals.length, m = queries.length;
        int[][] q = new int[m][2];//扩展queries，使排序后保留序号信息
        for (int i = 0; i < m; i++)
            q[i] = new int[]{i, queries[i]};
        Arrays.sort(q, (q1, q2) -> q2[1] - q1[1]);//从大到小排序查询
        Arrays.sort(intervals, (i1, i2) -> i2[1] - i1[1]); //按右边界从大到小排序区间
        int inter = 0;//已入堆的区间数量
        int[] ans = new int[m];//查询对应的答案
        for (int i = 0; i < m; i++) {
            int id = q[i][0], target = q[i][1];//查询序号、目标值
            while (inter < n && intervals[inter][1] >= target)//将右边界不小于当前目标值的区间入堆
                pq.add(intervals[inter++]);
            while (!pq.isEmpty() && pq.peek()[0] > target)//依次推出堆首左边界大于当前目标值的区间
                pq.poll();
            if (pq.isEmpty())//堆空说明没有符合条件的区间
                ans[id] = -1;
            else//堆首区间即为符合条件的最小区间
                ans[id] = pq.peek()[1] - pq.peek()[0] + 1;
        }
        System.out.println(Arrays.toString(ans));
    }
}
