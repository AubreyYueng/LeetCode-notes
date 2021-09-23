package _2020.contest.bi;

/**
 * Created by Yiyun On 9/5/20 10:50
 *
 * 1572. Matrix Diagonal Sum
 */
public class MatrixDiagonalSum {

    public int diagonalSum(int[][] mat) {
        if (mat == null || mat.length == 0)
            return 0;
        int n = mat.length;
        if (mat[0].length != n)
            return 0;

        int res = 0;
        for (int i = 0; i < mat.length; i++) {
            int[] row = mat[i];
            for (int j = 0; j < row.length; j++) {
                int num = row[j];
                if (num > 0 && (i == j || (i + j) == n-1 )) {
                    res += row[j];
                    row[j] = -1;
                }
            }
        }

        return res;
    }

}
