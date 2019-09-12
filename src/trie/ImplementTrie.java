package trie;

/**
 * Created by Yiyun On 2019/9/3 22:50
 *
 * 208. Implement Trie (Prefix Tree)
 *
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 *
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 *
 * TODO: Trie 前缀树: 又称单词查找树，是一种树形结构，是一种哈希树的变种。典型应用是用于统计，排序和保存大量的字符串（但不仅限于字符串），所以
 * TODO: 经常被搜索引擎系统用于文本词频统计。它的优点是：利用字符串的公共前缀来减少查询时间，最大限度地减少无谓的字符串比较，查询效率比哈希树高。
 * TODO: 它有3个基本性质: 1. 根节点不包含字符，除根节点外每一个节点都只包含一个字符；
 * TODO:                2. 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串；
 * TODO:                3. 每个节点的所有子节点包含的字符都不相同。
 */
public class ImplementTrie {

    public static class Trie {

        private static class TrieNode {

            private TrieNode[] links;

            // R links to node children
            private final int R = 26;

            private boolean isEnd;

            public TrieNode() {
                this.links = new TrieNode[R];
            }

            public TrieNode get(char ch) {
                return links[ch - 'a'];
            }

            public boolean containsKey(char ch) {
                return get(ch) != null;
            }

            public void put(char ch, TrieNode node) {
                links[ch - 'a'] = node;
            }

            public boolean isEnd() {
                return isEnd;
            }

            public void setEnd(boolean end) {
                isEnd = end;
            }
        }

        private TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode currNode = root;
            for (int i = 0; i < word.length(); i++) {
                char currCh = word.charAt(i);
                if (!currNode.containsKey(currCh))
                    currNode.put(currCh, new TrieNode());
                currNode = currNode.get(currCh);
            }
            currNode.setEnd(true);
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode currNode = root;
            for (int i = 0; i < word.length(); i++) {
                char currCh = word.charAt(i);
                if (!currNode.containsKey(currCh))
                    return false;
                currNode = currNode.get(currCh);
            }
            return currNode.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode currNode = root;
            for (int i = 0; i < prefix.length(); i++) {
                char currCh = prefix.charAt(i);
                if (!currNode.containsKey(currCh))
                    return false;
                currNode = currNode.get(currCh);
            }
            return true;
        }
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
