public class LC1023 {
    public static void main(String[] args) {
        camelMatch(new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}, "FB");
        //[true,false,true,true,false]
        camelMatch(new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}, "FoBa");
        //[true,false,true,false,false]
        camelMatch(new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}, "FoBaT");
        //[false,true,false,false,false]
    }
    public static void camelMatch(String[] queries, String pattern) {
        System.out.println(isMatch("ForceFeedBack", "FoBaT"));
        for (String query: queries) {
            System.out.println(isMatch(query, pattern));
        }
    }
    public static boolean isMatch(String query, String pattern) {
        int i = 0, j = 0;
        while (i < query.length() && j < pattern.length()) {
            char q = query.charAt(i), p = pattern.charAt(j);
            if (q == p) {
                i++;
                j++;
            } else {
                if (Character.isUpperCase(q)) return false;
                else if (Character.isLowerCase(p)) return false;
                i++;
            }
        }
        if (j < pattern.length()) return false;
        while (i < query.length()) {
            if (Character.isUpperCase(query.charAt(i++))) return false;
        }
        return true;
    }

}
