package _2021.pre_codes;

import org.junit.Test;

/**
 * Created by Yiyun On 2021/7/13 12:46
 */
public class pre_7 {

    public int parenthesizations(int n) {
        int[][] c = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i-1][j] + c[i][j-1];
            }
        }
        return c[n][n];
    }

    @Test
    public void case1() {
        for (int i = 0; i <= 8; i++) {
            System.out.println("parenthesizations("+i+"): " + parenthesizations(i));
        }
    }
}
