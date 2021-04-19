package _2021.kickstart.roundb;

import java.util.Scanner;

/**
 * Created by Yiyun On 2021/4/18 21:13
 * Increasing Substring
 */
public class _1 {

    private static int[] longestSubstr(String s, int n) {
        int[] res = new int[n];
        res[0] = 1;
        char[] chars = s.toCharArray();
        for (int i = 1; i < n; i++) {
            if (chars[i] > chars[i-1])
                res[i] = res[i-1] + 1;
            else
                res[i] = 1;
        }
        return res;
    }

    private static String format(int[] res, int caseNum) {
        StringBuilder sb = new StringBuilder();
        sb.append(res[0]);
        for (int i = 1; i < res.length; i++) {
            sb.append(' ');
            sb.append(res[i]);
        }
        return String.format("Case #%d: %s", caseNum, sb);
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int cases = Integer.parseInt(in.nextLine());
        for (int i = 0; i < cases; i++) {
            int len = Integer.parseInt(in.nextLine());
            String s = in.nextLine();
            int[] res = longestSubstr(s, len);
            System.out.println(format(res, i+1));
        }
    }
}
