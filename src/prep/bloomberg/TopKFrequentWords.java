package prep.bloomberg;

import java.util.*;
import java.util.function.Function;

/**
 * Created by Yiyun On 2021/2/5 00:12
 *
 * 692. Top K Frequent Words
 */
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String w : words) {
            cnt.put(w, cnt.getOrDefault(w, 0)+1);
        }

        // build min heap (instead of max heap)
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator
                .comparing((Function<String, Integer>) cnt::get)
                .thenComparing(Comparator.reverseOrder()));

        for (Map.Entry<String, Integer> en : cnt.entrySet()) {
            pq.offer(en.getKey());
            if (pq.size() > k) pq.poll();
        }

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        Collections.reverse(res);
        return res;
    }

}
