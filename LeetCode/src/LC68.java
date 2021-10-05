import java.util.ArrayList;
import java.util.List;

public class LC68 {
    public static void main(String[] args) {
        fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
        fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"}, 16);
        fullJustify(new String[]{"Science","is","what","we","understand","well","enough","to","explain",
                "to","a","computer.","Art","is","everything","else","we","do"}, 20);
    }

    public static void fullJustify(String[] words, int width) {
        List<String> res = new ArrayList<>();
        int len = 0, start = 0, j = 0;
        while (j < words.length) {
            int newLen = len > 0 ? len + 1 + words[j].length() : len + words[j].length();
            if (newLen <= width) {
                len = newLen;
                j++;
            } else {
                res.add(adjustRow(start, j - 1, words, width - len));
                start = j;
                len = 0;
            }
        }
        if (len  > 0) {
            res.add(adjustRow(start, j - 1, words, width - len));
        }
        res.stream().forEach(line -> System.out.println("|" + line + "|"));
    }
    public static String adjustRow(int start, int end, String[] words, int extraSpace) {
        String res = words[start];
        if (start == end) {
            return res + fillSpace(extraSpace);
        }
        if (end == words.length - 1) {
            for (int i = start + 1; i <= end; i++) {
                res += " " + words[i];
            }
            return res + fillSpace(extraSpace);
        }
        int n = extraSpace / (end - start), m = extraSpace % (end - start);
        for (int i = start + 1; i <= end; i++) {
            res += fillSpace(1 + n + (m-- > 0 ? 1 : 0)) + words[i];
        }
        return res;
    }
    public static String fillSpace(int n) {
        return new String(new char[n]).replace('\0', ' ');
    }
}
