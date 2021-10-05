import com.oracle.jrockit.jfr.Producer;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LC1386 {
    public static void main(String[] args) {
//        System.out.println(maxNumberOfFamilies(3, new int[][]{{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}}));
//        System.out.println(maxNumberOfFamilies(2, new int[][]{{2, 1}, {1, 8}, {2, 6}}));
//        System.out.println(maxNumberOfFamilies(4, new int[][]{{4, 3}, {1, 4}, {4, 6}, {1, 7}}));
//        sort(new int[]{2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8}, new int[]{2, 1, 8, 3});
        Filter a = new MinSizeFilter(12);
        Filter b = new TypeFilter(1);
        Filter c = new Filter() {
            @Override
            public boolean apply(File file) {
                Random random = new Random();
                boolean r = random.nextBoolean();
                System.out.println("boolean filter:" + r);
                return r;
            }
        };
        File f = new File();
        f.type = 2;
        f.size = 3;
        Filter all = a.or(b).or(c);
        System.out.println(a.or(b).or(c).apply(f));
        Function<String, String> ff = s -> "A" + s;
        System.out.println(ff.apply("a"));
    }

    static class File {
        String name;
        int size;
        int type;
        boolean isDirectory;
        File[] children;
    }

    @FunctionalInterface
    interface Filter {
        boolean apply(File file);
        default Filter and(Filter f) {
            return new AndFilter(f, this);
        };
        default Filter or(Filter f) {
            return new OrFilter(f, this);
        };
    }
    static class AndFilter implements Filter {
        private List<Filter> filters = new ArrayList<>();
        public AndFilter(Filter a, Filter b) {
            this.filters.add(a);
            this.filters.add(b);
        }
        @Override
        public boolean apply(File file) {
            for (Filter f : filters) {
                if (!f.apply(file)) return false;
            }
            return true;
        }
    }
    static class OrFilter implements Filter {
        public OrFilter(Filter a, Filter b) {
            this.filters.add(a);
            this.filters.add(b);
        }
        private List<Filter> filters = new ArrayList<>();
        @Override
        public boolean apply(File file) {
            for (Filter f : filters) {
                if (f.apply(file)) return true;
            }
            return false;
        }
    }

    static class MinSizeFilter implements Filter {
        int minSize;
        public MinSizeFilter(int minSize) {
            this.minSize = minSize;
        }
        @Override
        public boolean apply(File file) {
            return file.size > minSize;
        }
    }

    static class TypeFilter implements Filter {
        int type;
        public TypeFilter(int type) {
            this.type = type;
        }
        @Override
        public boolean apply(File file) {
            return file.type == type;
        }
    }

    public static void sort(int[] a, int[] b) {
        System.out.println(Arrays.toString(a) + "|" + Arrays.toString(b));
        Map<Integer, Integer> m = new HashMap<>();
        for (int i : a) {
            m.put(i, m.getOrDefault(i, 0) + 1);
        }
        int[] res = new int[a.length];
        int idx = 0;
        for (int seq : b) {
            for (int i = 0; i < m.get(seq); i++) {
                res[idx++] = seq;
            }
            m.remove(seq);
        }
        for (int origin : a) {
            if (m.get(origin) != null) {
                res[idx++] = origin;
            }
        }
        System.out.println(Arrays.toString(res));
    }

    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int[][] seats = new int[n][10];
        for (int[] reserved : reservedSeats) {
            int row = reserved[0] - 1;
            int col = reserved[1] - 1;
            seats[row][col] = 1;
        }
        int res = 0;
        for (int[] seat : seats) {
            res += count(seat);
        }
        return res;
    }
    public static int count(int[] row) {
        int[] starts = new int[]{ 1, 3, 5};
        int res = 0;
        for (int start : starts) {
            if (row[start] == 1) continue;
            int end = start + 1;
            for (; end <= start + 3; end++) {
                if (row[end] == 1) break;
                row[end] = 1;
            }
            if (end > start + 3) res++;
        }
        return res;
    }
}
//2. reverse start to the kth node from the link
//系列软件和dependencies，要求找出安装顺序，就是个拓扑排序dfs的题，followup要求安装失败的时候需要卸载之前安装的包，这一轮的
//BQ: 超出自己responsibility的事，transfer project给,
//critical feedback, project deadline, some ways to make the project easier, redo 做了一半的project的经历,用接单方法解决复杂问题
//System design: 设计类似谷歌bookmark, 要sync到不同的device, 假设100M users
//股票1,2,3, 可惜3我只会暴力解
//一定要准备和manager and 同事吵架🌰/赶不上ddl实🌰/CR冲突🌰/设计doc出现问题🌰/如何提出意见improve组里efficiency🌰/
// 工作中做过什么innovation🌰/我们组的service怎么实现的，有人给你design doc提错误的意见🌰，
// 以及很多我都不愿回忆的difficult question，必须要举出实🌰，第一轮mgr烙印感觉自己组做什么的也不太清楚，
// 问的BQ问题却一个比一个mean，不禁让我怀疑这个亚麻音乐组wlb是不是超级差。希望有经验的小伙伴来回答一下
//very tough questioning, including like "did you receive critical review and negative feedback
// about your performance from your managers?", "how did you take uninformed decision/risk?",
// drilling down deep. The manager asks questions outside of LP and I was a bit shocked!
//2. what impact or influence did your decision have?
//比如你有啥项目现在回想起来能做得好一些的？别人和你冲突怎么办，你过去最大的失败项目是啥，你以前没按时deliver项目时候咋办（我告诉
//. 基本的string manipulation，给字符串里的价格打八折，再输出结果，followup 有三问，第三问是和currency denominations有关，
// 如何round down 到最接近的Denomination。
//因為這就包含了你如何deliver result（交付project）,
//  當中有什麼難的地方然後自己克服了(Dive Deep/Bias for action）你如何查資料，如何看code,如何提升自己的能力去做這個project。
//  即使去問人也是可以說的(Dive Deep/Bias for action),比如說有domain knowledge的人很難約，你怎麼約會到 他，你怎麼去push(Owevership)
//  剛剛這個project就可以給你，一個deliver result的case, 兩個（Dive Deep/ Bias for action), 一個Owevership的case 。
//  1. 有沒有project無法交付的故事 2. 有沒有你克服難題的故事 3. 有沒有與同事合作遇到困難的故事
//* 做超出自己责任范围的事情；一个自己回顾觉得可以做的更好的项目
//* 为用户 went above and beyond的例子；用简单工具解决复杂问题的例子
//working on project then realize your approach not correct.
//tell me an innovative solution you had and how did you dive in the problem
//Teammate challege you and try to make you change your solution
//Permutation in String 字符串中的全排列  stock sell
//        Critical feedback
//        Work beyond responsibility
//        Tight deadline
//        Make a decision yourself
//        Make a decision with little information
//        Make impact
//        Disagreement with teammates
//        Disagreement with manager
//        What do you want to improve if you have a chance to implement a previous project again
//        Overcome a difficulty
//        Take a risk to d
//        compile dependency order 类似 Course schedule on leetcode (Topology sort)
//follow up:图有环怎么办 （check 一下最后出来的order list 是不是有所有的node, 如果没有就表示有环）
/*
 bq1：greatest achievement that 超过了你一开始的预期 怎么办到的
 bq2: Hard quesion you have solved
 bq3: hard decision you have to made for long term gain*/
//Tell me a time you dive deep into a problem and solve it