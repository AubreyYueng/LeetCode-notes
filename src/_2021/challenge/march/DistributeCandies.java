package _2021.challenge.march;

import java.util.HashSet;

/**
 * Created by Yiyun On 2021/3/1 23:56
 */
public class DistributeCandies {

    public int distributeCandies(int[] candyType) {
        HashSet<Integer> set = new HashSet<>();
        for(int c: candyType) {
            set.add(c);
        }
        return Math.min(candyType.length / 2, set.size());
    }
}
