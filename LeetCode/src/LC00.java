import java.util.*;
import java.util.stream.Collectors;

public class LC00 {
    public static void main(String ...args) {
        parse_accept_language("fr-CA, fr-FR", new String[]{"en-US", "fr-FR"});
        //System.out.println(res);
        parse_accept_language("en-US, fr-CA, fr-FR", new String[]{"fr-FR", "en-US"});
        //System.out.println(res);
        parse_accept_language("fr-FR, fr", new String[]{"en-US", "fr-CA", "fr-FR"});
        //System.out.println(res);
        parse_accept_language("en-US, *", new String[]{"fr-FR", "fr-CA", "fr-BG", "en-US"});
        //parse_accept_language("fr-FR;q=1, fr-CA;q=0, fr;q=0.5", new String[]{"fr-FR", "fr-CA", "fr-BG"});
        //parse_accept_language("fr-FR;q=1, fr-CA;q=0, *;q=0.5", ["fr-FR", "fr-CA", "fr-BG", "en-US"])
        //parse_accept_language("fr-FR;q=1, fr-CA;q=0.8, *;q=0.5", ["fr-FR", "fr-CA", "fr-BG", "en-US"])
    }
    public static List<String> parse_accept_language(String acceptLanguage, String[] supportLanguage) {
        List<String> languages = new LinkedList<>(Arrays.asList(supportLanguage));
        List<String> rules = Arrays.stream(acceptLanguage.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        List<String> res = new ArrayList<>();
        for (String rule : rules) {
            ListIterator<String> it = languages.listIterator();
            while (it.hasNext()) {
                String support = it.next();
                if (support.startsWith(rule) || rule.equals("*")) {
                    res.add(support);
                    it.remove();
                }
            }
        }
        System.out.println(res);
        return null;
    }

}
