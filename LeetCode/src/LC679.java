import java.util.*;

public class LC679 {
    public static void main(String[] args) {
        judgePoint24(new int[]{4, 1, 8, 7});
    }
    static class Item {
        String str;
        double value;
        public Item(String str, double value) {
            this.str = str;
            this.value = value;
        }
    }
    public static void judgePoint24(int[] arr) {
        for (int i = 0, j = 1; j < arr.length; j++) {
            int a = -1, b = -1;
            for (int k = 0; k < arr.length; k++) {
                if (k != i && k != j) {
                    if (a == -1) a = k;
                    else b = k;
                }
            }
            List<Item> group1 = compute(arr[i]+"", arr[i], arr[j]+"", arr[j]);
            List<Item> group2 = compute(arr[a]+"", arr[a], arr[b]+"", arr[b]);
            for (Item item1 : group1) {
                for (Item item2 : group2) {
                    for (Item item: compute(item1.str, item1.value, item2.str, item2.value)) {
                        if (is24(item.value)) System.out.println(item.str + "= 24");
                    }
                }
            }
        }
    }
    public static boolean is24(double a) {
        return Math.abs(24 - a) < 0.001;
    }
    public static List<Item> compute(String a, double i, String b, double j) {
        return Arrays.asList(new Item(String.format("(%s + %s)", a, b), i + j),
                new Item(String.format("(%s - %s)", a, b), i - j),
                new Item(String.format("(%s - %s)", b, a), j - i),
                new Item(String.format("(%s * %s)", a, b), i * j),
                new Item(String.format("(%s / %s)", a, b), i / j),
                new Item(String.format("(%s / %s)", b, a), j / i));
    }
}
