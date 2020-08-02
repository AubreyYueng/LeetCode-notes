package trie.medium;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2019/9/3 22:50
 *
 * 208. Implement Trie (Prefix Tree)
 */
public class ImplementTrie {

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    public static class Trie_review20200801 {
        public static class TNode {
            private TNode[] nodes = new TNode[26];
            private boolean last;   // if last is true, nodes are all null

            private void put(char ch) {
                nodes[ch-'a'] = new TNode();
            }

            private boolean exists(char ch) {
                return get(ch) != null;
            }

            private TNode get(char ch) {
                return nodes[ch-'a'];
            }
        }

        TNode node;

        /** Initialize your data structure here. */
        public Trie_review20200801() {
            this.node = new TNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TNode curr = node;
            char[] chars = word.toCharArray();
            for (char ch : chars) {
                if (!curr.exists(ch)) {
                    curr.put(ch);
                }
                curr = curr.get(ch);
            }
            curr.last = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TNode curr = node;
            char[] chars = word.toCharArray();
            for (char ch : chars) {
                if (!curr.exists(ch)) return false;
                curr = curr.get(ch);
            }
            return curr.last;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TNode curr = node;
            char[] chars = prefix.toCharArray();
            for (char ch : chars) {
                if (!curr.exists(ch)) return false;
                curr = curr.get(ch);
            }
            return true;
        }
    }

    @Test
    public void case1() {
        Trie_review20200801 trie = new Trie_review20200801();
        assertFalse(trie.startsWith("a"));
    }

    @Test
    public void case2() {
        Trie_review20200801 trie = new Trie_review20200801();
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("apple"));
        trie.insert("app");
        assertTrue(trie.search("app"));
    }


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