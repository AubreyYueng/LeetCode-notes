package dynamicprogramming.medium;

/**
 * Created by Yiyun On 2020/2/6 15:55
 *
 * 647. Palindromic Substrings
 * https://leetcode.com/problems/palindromic-substrings/
 *
 */
public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        // f(i, j): if palindromic between index (i, j)
        // f(i, j) = true;  if (i == j)
        // f(i, j) = false; if (i != j)
        // then update f(i, j), set true if f(i+1, j-1)
        // we can imagine a matrix(consider j>=i only) from the conclusion above
        if (s == null || s.length() == 0)
            return 0;
        int cnt = 0;
        int len = s.length();
        boolean[][] res = new boolean[len][len];
        for (int d = 0; d < len; d++) {     // d for distance
            for (int i = 0; i < len-d; i++) {
                int j = i+d;
                char ci = s.charAt(i);
                char cj = s.charAt(j);
                if (ci == cj)
                    res[i][j] = (i + 1 >= j - 1) || res[i + 1][j - 1];

                if (res[i][j])
                    cnt++;
            }
        }
        return cnt;
    }

}

