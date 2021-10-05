import java.util.HashMap;
import java.util.Map;

public class LC166 {
    public static void main(String[] args) {
        System.out.println(fractionToDecimal(11, 789));
        System.out.println(fractionToDecimal(1, 16));
        System.out.println(fractionToDecimal(5, 4));
        System.out.println(fractionToDecimal(2, 3)); //0.(6)
        System.out.println(fractionToDecimal(1, 70));
        System.out.println(fractionToDecimal(1, 17));
    }
    public static String fractionToDecimal(int numerator, int denominator) {
        int a = numerator / denominator;
        int remain = numerator % denominator;
        if (remain == 0) {
            return a + "";
        }
        String res = "";
        Map<Integer, Integer> cir = new HashMap<>();
        while (remain > 0) {
            if (cir.containsKey(remain)) {
                int pos = cir.get(remain);
                String first = pos > 0 ? res.substring(0, pos) : "";
                String second = res.substring(pos);
                return a + "." + first + "(" + second + ")";
            }
            res += (remain * 10) / denominator;
            cir.put(remain, res.length() - 1);
            remain = (remain * 10) % denominator;
        }
        return a + "." + res;
    }
}
