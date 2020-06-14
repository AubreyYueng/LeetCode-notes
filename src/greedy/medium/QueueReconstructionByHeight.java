package greedy.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2020/6/14 02:29
 *
 * 406. Queue Reconstruction by Height
 */
public class QueueReconstructionByHeight {

    // Sort people:
    //   In the descending order by height.
    //   Among the guys of the same height, in the ascending order by k-values.
    // Take guys one by one, and place them in the output array at the indexes equal to their k-values.
    // Return output array.
    public int[][] reconstructQueue(int[][] people) {
        // higher first, within same height then smaller k first(how many people before is higher)
        Arrays.sort(people, Comparator.comparing((int[] arr) -> arr[0]).reversed().thenComparing(arr -> arr[1]));

        // place them one by one according to k as index
        List<int[]> res = new LinkedList<>();
        for(int[] p : people){
            res.add(p[1], p);
        }

        return res.toArray(new int[][]{});
    }

}
