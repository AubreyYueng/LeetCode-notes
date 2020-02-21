package design.medium;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Yiyun On 2020/2/20 21:28
 *
 * 146. LRU Cache
 * https://leetcode.com/problems/lru-cache/
 */
class LRUCache extends LinkedHashMap<Integer, Integer> {

    // We can also use Hashmap+doubleLinkedList
    private int capacity;

    public LRUCache(int capacity) {
        // why load-factor is 0.75:
        //      1. https://www.jianshu.com/p/64f6de3ffcc1
        //      2. https://www.cnblogs.com/a294098789/p/5323032.html
        // figure out the meaning of accessOrder
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
