package bit.manipulation.medium;

import java.util.HashMap;

/**
 * Created by Yiyun On 2020/7/3 22:37
 *
 * 957. Prison Cells After N Days
 */
public class PrisonCellsAfterNDays {

    // "both occupied or both vacant, then the cell becomes occupied", we can relate it to XOR
    // apply the XOR and NOT operations sequentially.
    // head and tail cells are particular, thus we should AND with 01...10
    // Time: min(N, 2^K): because the states are repetitive

    // The following codes are mostly copied from LC solution
    public int[] prisonAfterNDays(int[] cells, int N) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        boolean fastForward = false;

        // step 1: convert cells to bitmap
        int bitmap = 0x0;
        for (int c : cells) {
            bitmap <<= 1;
            bitmap |= c;
        }

        // step 2: run simulation with hashmap
        while (N > 0) {
            if (!fastForward) {
                if (!seen.containsKey(bitmap)) {
                    seen.put(bitmap, N);
                } else {
                    // the length of cycle is seen.val - N
                    N %= seen.get(bitmap) - N;
                    fastForward = true;
                }
            }

            // check the remaining steps TODO: I think we don't need to compute again if fastForward
            if (N > 0) {
                N--;
                bitmap = ~(bitmap << 1) ^ (bitmap >> 1);
                // set the head and tail to zero
                bitmap = bitmap & 0x7e;
            }
        }

        // step 3: transform bitmap to state cells for output
        int res[] = new int[cells.length];
        for (int i = cells.length - 1; i >= 0; i--) {
            res[i] = bitmap & 0x1;
            bitmap = bitmap >> 1;
        }
        return res;
    }

}
