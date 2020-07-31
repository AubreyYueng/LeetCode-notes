package backtracking.medium;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2019/8/30 00:28
 * 79. Word Search
 */
public class WordSearch {

    public boolean exist_review20200730(char[][] board, String word) {
        if (board == null || board.length == 0) return false;
        return new DFS_20200730(board, word).res;
    }

    private static class DFS_20200730 {
        static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // given parameters
        char[][] board;
        String word;

        int M;
        int N;
        int W;
        boolean[][] used;
        boolean res;

        DFS_20200730(char[][] board, String word) {
            this.board = board;
            this.word = word;
            this.M = board.length;
            this.N = board[0].length;
            this.W = word.length();
            used = new boolean[M][N];

            int w = 0;
            for (int m = 0; m < M; m++) {
                for (int n = 0; n < N; n++) {
                    if (dfs(w, m, n)) {
                        res = true;
                        break;
                    }
                }
            }
        }

        private boolean dfs(int w, int m, int n) {
            if (w == W) return true;
            if (m < 0 || m >= M || n < 0 || n >= N) return false;
            if (used[m][n] || board[m][n] != word.charAt(w)) return false;

            used[m][n] = true;
            for (int[] dir : dirs) {
                if (dfs(w+1, m+dir[0], n+dir[1])) return true;
            }
            used[m][n] = false;
            return false;
        }
    }

    public boolean exist(char[][] board, String word) {
        int rowSize = board.length;
        int colSize = board[0].length;
        boolean[][] used = new boolean[rowSize][colSize];

        for (int r = 0; r < used.length; r++) {
            for (int c = 0; c < used[r].length; c++) {
                if (dfs(board, used, word, 0, r, c))
                    return true;
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, boolean[][] used, String word, int pos, int row, int col) {
//        System.out.println("pos: " + pos + ", row: " + row + ", col: " + col);
        if (row < 0 || row >= board.length
                || col < 0 || col >= board[0].length
                || pos >= word.length()
                || board[row][col] != word.charAt(pos)
                || used[row][col]) {
            return false;
        }
        if (pos == word.length()-1)
            return true;

//        System.out.println("pos: " + pos + ", row: " + row + ", col: " + col
//                + ", char: " + word.charAt(pos) + ", cell: " + board[row][col]);
        used[row][col] = true;
        boolean res = dfs(board, used, word, pos+1, row, col-1)
                || dfs(board, used, word, pos+1, row, col+1)
                || dfs(board, used, word, pos+1, row+1, col)
                || dfs(board, used, word, pos+1, row-1, col);
        used[row][col] = false;
        return res;
    }

    @Test
    public void case1() {
        assertTrue(exist_review20200730(new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}}, "ABCCED"));
    }

    @Test
    public void case2() {
        assertFalse(exist_review20200730(new char[][]{{'b'},{'a'},{'b'}}, "bbabab"));
    }

    @Test
    public void case3() {
        assertTrue(exist_review20200730(new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}}, "SEE"));
    }
}
