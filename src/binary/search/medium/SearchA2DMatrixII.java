package binary.search.medium;

import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yiyun On 2020/3/9 15:33
 *
 * 240. Search a 2D Matrix II
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class SearchA2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        // some related analysis(eg: zigzag-ed binary, divide and conquer, etc):
        // https://stackoverflow.com/questions/2457792/how-do-i-search-for-a-number-in-a-2d-array-sorted-left-to-right-and-top-to-botto/2458113#2458113

        // Note!!! It's different from [74. Search a 2D Matrix] which is through identifying row/col first then col/row because in 74 "The first integer of each row is greater than the last integer of the previous row."

        // 模板: https://www.acwing.com/blog/content/31/
        // 没有duplicate且寻找==target的，找到就即刻返回(无需返回移动指针), 最后比较nums[l]和target, 寻找第一个<=target的元素用模板1(275. H-Index II), 寻找最后一个<=target的元素用模板2(69. Sqrt(x))
        // 答案虽然落在左区间, 但mid有可能不是答案, 此时应该把[l, r]更新成 [l, mid - 1]，也就是需要让 r = mid - 1, 就用模板2

        // time complexity: O(lg(n!)) = O(\sum_{k==1}^{n}lg(k))
        // space: O(1)
        if (matrix == null || matrix.length==0 || matrix[0].length==0)
            return false;
        return new Helper(matrix, target).find();
    }

    public boolean searchMatrix_spacereduction(int[][] matrix, int target) {
        // search space reduction solution
        // time: O(n+m)
        // space: O(1)
        // starting from bottom left
        if (matrix == null || matrix.length==0 || matrix[0].length==0)
            return false;
        int col = matrix[0].length;
        int i = matrix.length-1;
        int j = 0;
        while (i >= 0 && j < col) {
            int num = matrix[i][j];
            if (num == target)
                return true;
            if (num < target)
                j++;
            else
                i--;
        }
        return false;
    }

    private static class Helper {
        int row;
        int col;
        int target;
        int[][] matrix;

        Helper(int[][] matrix, int target) {
            this.matrix = matrix;
            this.row = matrix.length;
            this.col = matrix[0].length;
            this.target = target;
        }

        private boolean find() {
            int size = Math.min(row, col);
            for (int i = 0; i < size; i++) {
                if (binarySearch(i, true) || binarySearch(i, false))
                    return true;
            }
            return false;
        }

        boolean binarySearch(int st, boolean vertical) {
            int r = vertical ? row-1 : col-1;
            int l = st;
            Function<Integer, Integer> getNum = mid -> vertical ? matrix[mid][st] : matrix[st][mid];
            while(l < r) {
                int mid = (l + r) / 2;
                int num = getNum.apply(mid);
                if (num == target)
                    return true;

                if (num > target)
                    r = mid;
                else
                    l = mid+1;
            }
            // 注意这里必要直接返回false
            return getNum.apply(l) == target;
        }
    }

    @Test
    public void case1() {
        int[][] matrix = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        assertTrue(searchMatrix(matrix, 19));
    }

    @Test
    public void case2() {
        int[][] matrix = {{1,1}};
        assertFalse(searchMatrix(matrix, 2));
    }

    @Test
    public void case3() {
        int[][] matrix = {{-5}};
        assertTrue(searchMatrix(matrix, -5));
    }

}
