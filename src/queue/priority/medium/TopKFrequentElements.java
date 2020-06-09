package queue.priority.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Yiyun On 2020/6/9 02:45
 *
 * 347. Top K Frequent Elements
 */
public class TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        for (int n : nums) {
            int cnt = cntMap.getOrDefault(n, 0)+1;
            cntMap.put(n, cnt);
        }

        int uniLen = cntMap.size();
        int[] unique = new int[uniLen];
        int i = 0;
        for (Integer uni : cntMap.keySet()) {
            unique[i++] = uni;
        }

        return new QuickSelectHelper(unique, cntMap).quickSelect(k);
    }

    private static class QuickSelectHelper {
        int[] unique;
        HashMap<Integer, Integer> cntMap;

        private QuickSelectHelper(int[] unique, HashMap<Integer, Integer> cntMap) {
            this.unique = unique;
            this.cntMap = cntMap;
        }

        private int[] quickSelect(int k) {
            int st = 0;
            int ed = unique.length-1;

            while (st < ed) {
                int pivot = st + new Random().nextInt(ed - st);
                pivot = partition(st, ed, pivot);
                if (pivot == k)
                    break;

                if (pivot < k) {
                    st = pivot + 1;     // don't forget about this '+1'
                } else
                    ed = pivot - 1;
            }

            return Arrays.copyOfRange(unique, 0, k);
        }

        private int partition(int st, int ed, int pivot) {
            int p = cntMap.get(unique[pivot]);
            swap(pivot, ed);

            int storeIdx = st;
            for (int i = st; i < ed; i++) {
                if (cntMap.get(unique[i]) > p)
                    swap(storeIdx++, i);
            }

            swap(storeIdx, ed);
            return storeIdx;
        }

        private void swap(int i, int j) {
            int tmp = unique[i];
            unique[i] = unique[j];
            unique[j] = tmp;
        }
    }

    @Test
    public void case1() {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
    }
}
