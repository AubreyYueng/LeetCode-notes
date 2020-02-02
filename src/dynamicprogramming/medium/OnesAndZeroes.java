package dynamicprogramming.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/2/1 21:19
 * 474. Ones and Zeroes
 * https://leetcode.com/problems/ones-and-zeroes/
 */
public class OnesAndZeroes {

    public int findMaxForm(String[] strs, int m, int n) {

        // f(m, n) = Math.max(
        //             f(m-x,n-y)+1,
        //             f(m,n)
        //           )

        int[][] res = new int[m+1][n+1];
        for(int i = 0; i < m+1; i++)
            for(int j = 0; j < n+1; j++)
                res[i][j] = 0;

        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            int x = 0;
            int y = 0;

            for (int h = 0; h < s.length(); h++) {
                if (s.charAt(h) == '0') x++;
                else y++;
            }

            // 需要注意的是因为这里采用的是prev+1, 所以应该由后面往回更新
            for (int j = m; j >= x; j--) {
                for (int k = n; k >= y; k--) {
                    int r = res[j][k];
                    int prev = res[j-x][k-y];
                    res[j][k] = Math.max(r, prev+1);
                }
            }


//            for(int[] row: res) {
//                System.out.println(Arrays.toString(row));
//            }
//            System.out.println();
        }

        return res[m][n];
    }

    @Test
    public void case1() {
        assertEquals(4, findMaxForm(new String[]{"10", "0001", "111001", "1", "0"}, 5, 3));
    }

}
