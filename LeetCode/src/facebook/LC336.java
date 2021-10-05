package facebook;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class LC336 {
    public static void main(String[] args) {
        palindromePairs(new String[]{"bat", "tab", "cat"});
        palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"});
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        diameterOfBinaryTree(root);
        ladderLength("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"});
        RandomContainer c = new RandomContainer();
        c.push(3);
        c.push(9);
        c.push(6);
        System.out.println(c.lastIdx + "," + c.arr);
        System.out.println("pop=" + c.popRandom());
        System.out.println(c.lastIdx + "," + c.arr);
        c.push(1);
        System.out.println(c.lastIdx + "," + c.arr);
        System.out.println("pop=" + c.popRandom());
        System.out.println(c.lastIdx + "," + c.arr);
        System.out.println("pop=" + c.popRandom());
        System.out.println(c.lastIdx + "," + c.arr);
        System.out.println("pop=" + c.popRandom());
        System.out.println(c.lastIdx + "," + c.arr);
        System.out.println("pop=" + c.popRandom());

        root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(-6);
        root.right.right = new TreeNode(2);
        findLargestSubtreeSum(root);
    }
    public static int dfs(TreeNode node, int[] res) {
        if (node == null) return 0;
        int left = dfs(node.left, res);
        int right = dfs(node.right, res);
        res[0] = Math.max(res[0], left + right + node.value);
        return left + right + node.value;
    }
    public static void findLargestSubtreeSum(TreeNode root) {
        int[] res = new int[1];
        res[0] = 0;
        dfs(root, res);
        System.out.println("max=" + res[0]);
    }
    static class RandomContainer {
        int lastIdx = 0;
        List<Integer> arr = new ArrayList<>();
        public void push(int value) {
            if (lastIdx < arr.size()) {
                arr.set(lastIdx, value);
            } else {
                arr.add(value);
            }
            lastIdx++;
        }
        public int popRandom() {
            if (lastIdx <= 0) return -1;
            int randomIdx = (int) (Math.random() * lastIdx);
            int value = arr.get(randomIdx);
            arr.set(randomIdx, arr.get(lastIdx - 1));
            lastIdx--;
            return value;
        }
    }
    public static void ladderLength(String beginWord, String endWord, String[] wordList) {
        Set<String> dict = new HashSet<>(Arrays.asList(wordList));
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        Set<String> visited = new HashSet<>();
        int dist = 0;
        while (!q.isEmpty()) {
            for (int k = q.size() - 1; k >= 0; k--) {
                String cur = q.poll();
                visited.add(cur);
                if (cur.equals(endWord)) {
                    System.out.println(endWord + "=>" + dist);
                    return;
                }
                for (int i = 0; i < cur.length(); i++) {
                    for (int j = 0; j < 26; j++) {
                        if (cur.charAt(i) - 'a' == j) continue;
                        String nextWord = cur.substring(0, i) + (char)(j + 'a') + cur.substring(i + 1);
                        if (dict.contains(nextWord) && !visited.contains(nextWord)) {
                            q.offer(nextWord);
                        }
                    }
                }
            }
            dist++;
        }
    }
    public static int maxDepth(TreeNode root, int[] res) {
        if (root == null) return 0;
        int left = maxDepth(root.left, res);
        int right = maxDepth(root.right, res);
        res[0] = Math.max(res[0], left + right);
        return Math.max(left, right) + 1;
    }
    public static void diameterOfBinaryTree(TreeNode node) {
        int[] res = new int[1];
        maxDepth(node, res);
        System.out.println(res[0]);
    }
    static class TreeNode {
        int value;
        TreeNode left, right;
        public TreeNode(int value) {
            this.value = value;
        }
    }
    static class TrieNode {
        int idx;
        TrieNode[] children = new TrieNode[26];
        List<Integer> palindIdx = new ArrayList<>();
        public TrieNode() {
            idx = -1;
        }
    }
    public static void insertWord(TrieNode root, String word, int idx) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int ch = word.charAt(i) - 'a';
            if (root.children[ch] == null) {
                root.children[ch] = new TrieNode();
            }
            if (isPalindrome(word, 0, i)) {
                root.palindIdx.add(idx);
            }
            root = root.children[ch];
        }
        root.idx = idx;
        root.palindIdx.add(idx);
    }
    private static void find(TrieNode root, String word, int idx) {
        for (int i = 0; i < word.length(); i++) {
            if (root.idx != -1 && root.idx != idx && isPalindrome(word, i, word.length() - 1)) {
                System.out.println(idx + "=>" + root.idx);
            }
            if (root.children[word.charAt(i) - 'a'] == null) return;
            root = root.children[word.charAt(i) - 'a'];
        }
        for (int i : root.palindIdx) {
            if (i != idx) {
                System.out.println(idx + "=>" + i);
            }
        }
    }
    private static boolean isPalindrome(String word, int left, int right) {
        while (left < right) {
            if (word.charAt(left++) != word.charAt(right--)) return false;
        }
        return true;
    }

    public static void palindromePairs(String[] words) {
        if (words == null || words.length == 0) return;
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            insertWord(root, words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            find(root, words[i], i);
        }
    }
}
