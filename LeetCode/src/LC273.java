public class LC273 {
    public static void main(String ...args) {
        System.out.println(numberToWords(123));
        System.out.println(numberToWords(12345));
        System.out.println(numberToWords(1234567));
        System.out.println(numberToWords(1234567891));
    }
    static String[] group = {"", "Thousand", "Million", "Billion"};
    static String[] from1to19 = {"One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen",
            "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    static String[] from20to90 = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private static String numberToWords(int num) {
        String res = "";
        int level = 0;
        while (num > 0) {
            res = toString(num % 1000) + " " + group[level++] + " " + res;
            num = num / 1000;
        }
        return res;
    }

    private static String toString(int num) {
        String res = "";
        if (num >= 100) {
            res = from1to19[num / 100 - 1] + " Hundred";
            num = num % 100;
        }
        if (num > 19) {
            res = res + " " + from20to90[num / 10 - 2];
            num = num % 10;
        }
        if (num != 0) {
            res = res + " " + from1to19[num - 1];
        }
        return res.trim();
    }
}
