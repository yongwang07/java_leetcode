public class LC208 {
    public static void main(String[] args) {
//        Trie wordTree = new Trie();
//        wordTree.insert("apple");
//        System.out.println(wordTree.search("apple"));
//        System.out.println(wordTree.search("app"));
//        System.out.println(wordTree.search("applee"));
//        wordTree.insert("app");
//        System.out.println(wordTree.search("app"));
        Trie prefixTree = new Trie();
        String[] arr = {"amazon", "ambzon", "amczon", "boy", "girl"}; //Ama2n, Amb2n, Amc2n, b1y, g2l
        for (String s : arr) {
            prefixTree.insert(s);
        }
        for (String s : arr) {
            int p = prefixTree.uniquePrefixIdx(s);
            String n = s.substring(0, p + 1) + (s.length() - (p + 1 + 1)) + s.charAt(s.length() - 1);
            System.out.println(s + "=>" + n);
        }
    }
    static class TrieNode {
        private TrieNode[] next = new TrieNode[26];
        private int preCnt = 0;
    }
    static class Trie {
        private TrieNode root = new TrieNode();
        public void insert(String s) {
            TrieNode curNode = root;
            for (char c : s.toCharArray()) {
                int idx = c - 'a';
                if (curNode.next[idx] == null) {
                    curNode.next[idx] = new TrieNode();
                }
                curNode.next[idx].preCnt++;
                curNode = curNode.next[idx];
            }
        }
        public int uniquePrefixIdx(String s) {
            TrieNode curNode = root;
            int idx = 0;
            while (idx < s.length() && curNode != null) {
                int i = s.charAt(idx) - 'a';
                if (curNode.next[i] != null && curNode.next[i].preCnt == 1) {
                    return idx;
                }
                curNode = curNode.next[i];
                idx++;
            }
            return -1;
        }
    }

//    static class TrieNode {
//        private TrieNode[] next;
//        private boolean isWord;
//        public TrieNode() {
//            this.next = new TrieNode[26];
//            this.isWord = false;
//        }
//    }
//    static class Trie {
//        private TrieNode root = new TrieNode();
//        public void insert(String s) {
//            TrieNode curNode = root;
//            for (char c : s.toCharArray()) {
//                int idx = c - 'a';
//                if (curNode.next[idx] == null) {
//                    curNode.next[idx] = new TrieNode();
//                }
//                curNode = curNode.next[idx];
//            }
//            curNode.isWord = true;
//        }
//        public boolean search(String s) {
//            int idx = 0;
//            TrieNode curNode = root;
//            while (idx < s.length() && curNode != null) {
//                char c = s.charAt(idx);
//                TrieNode nextNode = curNode.next[c - 'a'];
//                if (nextNode == null) return false;
//                idx++;
//                curNode = nextNode;
//            }
//            if (curNode == null) return false;
//            return curNode.isWord;
//        }
//    }
}
