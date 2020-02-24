package bfs.medium;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/24 17:27
 *
 * 127. Word Ladder
 * https://leetcode.com/problems/word-ladder/
 */
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Next time we can consider using Bidirectional BFS
        if (wordList == null || wordList.isEmpty() || !wordList.contains(endWord))
            return 0;
        int len = beginWord.length();

        //"The problem boils down to finding the shortest path from a start node to a destination node, if there exists one"
        // 1. pre-process a general map
        Map<String, Set<String>> general = new HashMap<>();
        for(String w:wordList) {
            for(String key: getGeneral(w, len)) {
                Set<String> value = general.getOrDefault(key, new HashSet<>());
                value.add(w);
                general.put(key, value);
            }
        }

        // 2. start BFS
        Set<String> visited = new HashSet<>();
        // Note1: actually LinkedList is a queue
        // Note2: actually Java has Pair
        LinkedList<Pair<String, Integer>> path = new LinkedList<>();
        path.add(new Pair<>(beginWord, 1));
        visited.add(beginWord);
        while (!path.isEmpty()) {
            Pair<String, Integer> node = path.remove();
            int level = node.getValue();
            for(String generalWord: getGeneral(node.getKey(), len)) {
//                System.out.println("generalW: " + generalWord);
                Set<String> nextWords = general.getOrDefault(generalWord, new HashSet<>());
                for(String nextW: nextWords) {
//                    System.out.println("nextW: " + nextW);
                    if (visited.contains(nextW)) continue;
                    visited.add(nextW);

                    if (nextW.equals(endWord))
                        return level+1;
                    path.add(new Pair<>(nextW, level+1));
                }
            }
        }

        return 0;
    }

    private Set<String> getGeneral(String w, int len) {
        Set<String> ans = new HashSet<>();
        for(int i = 0; i < len; i++) {
            ans.add(w.substring(0,i)+"*"+w.substring(i+1,len));
        }
        return ans;
    }

    @Test
    public void case1() {
        assertEquals(5, ladderLength("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
    }

}
