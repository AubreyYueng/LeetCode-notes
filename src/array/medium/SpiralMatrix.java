package array.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2020/6/30 21:56
 *
 * 54. Spiral Matrix
 */
public class SpiralMatrix {

    // Layer-by-layer (learn from LC Solution)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0)
            return res;

        int r1 = 0;
        int r2 = matrix.length-1;
        int c1 = 0;
        int c2 = matrix[0].length-1;

        while (r1 <= r2 && c1 <= c2) {
            for (int j = c1; j <= c2; j++) {
                res.add(matrix[r1][j]);
            }

            for (int i = r1+1; i <= r2; i++) {
                res.add(matrix[i][c2]);
            }

            // Be careful about this condition!!! Easily missed.
            if (r1 != r2 && c1 != c2) {
                for (int j = c2 - 1; j >= c1; j--) {
                    res.add(matrix[r2][j]);
                }
                for (int i = r2 - 1; i >= r1 + 1; i--) {
                    res.add(matrix[i][c1]);
                }
            }

            r1++;
            r2--;
            c1++;
            c2--;

        }

        return res;
    }

}
