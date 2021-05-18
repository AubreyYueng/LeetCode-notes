package _2021.contest._0515;

/**
 * Created by Yiyun On 2021/5/15 10:50
 */
public class _2 {

    public int[] memLeak(int memory1, int memory2) {
        int i = 0;
        int[] prev = {0, memory1, memory2};
        while (++i > 0) {
            if (memory1 >= memory2) {
                memory1 -= i;
            } else {
                memory2 -= i;
            }
            if (memory1 < 0 || memory2 < 0)
                return prev;
            prev = new int[]{i, memory1, memory2};
        }
        return prev;
    }
}
