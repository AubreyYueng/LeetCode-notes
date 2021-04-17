package _2021.challenge.april;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yiyun On 2021/4/16 21:43
 * 642. Design Search Autocomplete System
 */
public class AutocompleteSystem {

    private static class Trie {
        int times;
        Trie[] branches = new Trie[27];
    }

    private Trie root;
    private String received;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.root = new Trie();
        this.received = "";
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            insert(received, 1);
            received = "";
            return new ArrayList<>();
        } else {
            received += c;
            return findByPrefix(received);
        }
    }

    private void insert(String s, int times) {
        Trie cur = root;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int id = toInt(ch);
            if (cur.branches[id] == null)
                cur.branches[id] = new Trie();
            cur = cur.branches[id];
        }
        cur.times += times; // 更新句尾节点的次数 其余节点的保持不变
    }

    private int toInt(char ch) {
        return ch == ' ' ? 26 : ch - 'a';
    }

    private List<String> findByPrefix(String s) {
        Trie entrance = root;
        for (char ch: s.toCharArray()) {
            Trie next = entrance.branches[toInt(ch)];
            if (next == null)
                return new ArrayList<>();
            entrance = next;
        }
        List<Info> res = new ArrayList<>();
        dfs(s, res, entrance);
        return res.stream()
                .sorted(
                        Comparator.comparing((Info info) -> info.times).reversed()
                                .thenComparing(info -> info.s)
                )
                .map(info -> info.s)
                .limit(3)
                .collect(Collectors.toList());

    }

    private void dfs(String curS, List<Info> result, Trie curTrie) {
        if (curTrie == null)
            return;
        if (curTrie.times > 0) {
            result.add(new Info(curS, curTrie.times));
        }
        for (int i = 0; i < 27; i++) {
            char ch = i == 26 ? ' ' : (char) ('a' + i);
            dfs(curS + ch, result, curTrie.branches[i]);
        }
    }

    private static class Info {
        String s;
        int times;

        Info(String s, int times) {
            this.s = s;
            this.times = times;
        }
    }

}
