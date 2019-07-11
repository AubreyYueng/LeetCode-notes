package binary.search.medium;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2019/7/11 22:28
 *
 * No. 74 Search a 2D Matrix
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 * Example 1:
 * Input:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 *
 * Example 2:
 * Input:
 * matrix = [
 * [1,   3,  5,  7],
 * [10, 11, 16, 20],
 * [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 *
 * TODO: case matrix = [[1]], target = 2 which requires us to set the proper bound: int high = matrix.length-1
 * TODO: case matrix = [[]] and case [] requires us to check the length of BOTH row and column.
 *
 */
public class SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;

        int rowNum = findRow(matrix, target);
        if (rowNum > 0) {
            int[] previousRow = matrix[rowNum-1];
            if (previousRow[previousRow.length-1] == target)
                return true;
        }

        int[] row = matrix[rowNum];
        if (row.length == 0)
            return false;

        int columnNum = findColumn(row, target);
        return row[columnNum] == target;
    }

    private int findRow(int[][] matrix, int target) {
        int low = 0;
        int high = matrix.length-1;
        while (low < high) {
            int mid = (low + high) / 2;
            int[] midRow =  matrix[mid];
            if (target > midRow[midRow.length-1])
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    private int findColumn(int[] row, int target) {
        int low = 0;
        int high = row.length-1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (target > row[mid])
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }

    @Test
    public void case1() {
        assertTrue(searchMatrix(new int[][]{{1,3,5,7}, {10,11,16,20}, {23, 30, 34, 50}}, 3));
    }

    @Test
    public void case2() {
        assertFalse(searchMatrix(new int[][]{{1,3,5,7}, {10,11,16,20}, {23, 30, 34, 50}}, 13));
    }

    @Test
    public void case3() {
        assertTrue(searchMatrix(new int[][]{{1,3,5,7}, {10,11,16,20}, {23, 30, 34, 50}}, 20));
    }

    @Test
    public void case4() {
        assertFalse(searchMatrix(new int[][]{{1}}, 2));
    }

    @Test
    public void case5() {
        assertFalse(searchMatrix(new int[][]{{}}, 1));
    }
}
