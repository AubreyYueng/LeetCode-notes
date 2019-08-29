package backtracking.medium;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2019/8/30 00:28
 *
 * 79. Word Search
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where 'adjacent' cells are those horizontally
 * or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 * board =
 * [
 *  ['A','B','C','E'],
 *  ['S','F','C','S'],
 *  ['A','D','E','E']
 * ]
 * Given word = 'ABCCED', return true.
 * Given word = 'SEE', return true.
 * Given word = 'ABCB', return false.
 *
 * TODO: 1. NOTE that 'The same letter cell may not be used more than once'
 * TODO: 2. how to initialize a size fixed array
 * TODO: 3. don't split string, use 'charAt'
 * TODO: 4. reason why set 'used[row][col] = false;' at the last of dfs method:
 * TODO:        if res is true, doesn't matter if it's set,
 * TODO:        if res is false, this cell should set to false
 */
public class WordSearch {

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
        assertTrue(exist(new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}}, "ABCCED"));
    }

    @Test
    public void case2() {
        assertFalse(exist(new char[][]{{'b'},{'a'},{'b'}}, "bbabab"));
    }
}
