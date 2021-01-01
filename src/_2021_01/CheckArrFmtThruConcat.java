package _2021_01;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yiyun On 2021/1/1 13:01
 *
 * 1640. Check Array Formation Through Concatenation
 */
public class CheckArrFmtThruConcat {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < pieces.length; i++) {
            int[] p = pieces[i];
            pos.put(p[0], i);
        }

        int i = 0;
        while (i < arr.length) {
            Integer idx = pos.get(arr[i]);
            if (idx == null) return false;
            int[] p = pieces[idx];
            if ((i + p.length) > arr.length)
                return false;

            for (int aP : p) {
                if (arr[i++] != aP)
                    return false;
            }
        }
        return true;

    }

}
