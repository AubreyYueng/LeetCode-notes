package backtracking.hard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yiyun On 2020/7/5 18:02
 * 51. N-Queens
 */
public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        Helper h = new Helper(n);
        h.dfs(0);
        return h.res;
    }

    /**
     queen attacks whoever in its diagonals, rows and cols.
     (/)diag1[3n]: row+ col+, row-col=const1 in (-n, n)
     (\)diag2[2n]: row- col+, row+col=const2 in [0, 2n-1]
     place a queen(i, j): diag1[i-j+n]=1, diag2[i+j]=1, rows(j)=1, col(i)=j
     */
    private static class Helper {
        int n;
        int diag1[];
        int diag2[];
        int rows[];
        int cols[];
        List<List<String>> res;

        public Helper(int n) {
            this.n = n;
            this.diag1 = new int[3*n];
            this.diag2 = new int[2*n];
            this.rows = new int[n];
            this.cols = new int[n];
            this.res = new ArrayList<>();
        }

        private void placeQueen(int i, int j) {
            this.diag1[i-j+n] = 1;
            this.diag2[i+j] = 1;
            this.rows[j] = 1;
            this.cols[i] = j;
        }

        private void removeQueen(int i, int j) {
            this.diag1[i-j+n] = 0;
            this.diag2[i+j] = 0;
            this.rows[j] = 0;
            this.cols[i] = 0;
        }

        private boolean underAttack(int i, int j) {
            return this.diag1[i-j+n] > 0 || this.diag2[i+j] > 0 || rows[j] > 0;
        }

        private void dfs(int i) {
            if (i == n)
                addRes();
            else {
                for (int j = 0; j < n; j++) {
                    if (!underAttack(i, j)){
                        placeQueen(i, j);
                        dfs(i+1);
                        removeQueen(i, j);
                    }
                }
            }
        }

        private void addRes() {
            List<String> curr = new ArrayList<>();
            char[] chars = new char[n];
            for (int i = 0; i < n; i++) {
                int j = cols[i];
                Arrays.fill(chars, '.');
                chars[j] = 'Q';
                curr.add(new String(chars));
                chars[j] = '.';
            }
            res.add(curr);
        }

    }

    @Test
    public void case1() {
        solveNQueens(4).forEach(System.out::println);
    }
}
