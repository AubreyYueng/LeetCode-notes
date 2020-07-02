package backtracking.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yiyun On 2020/7/1 20:51
 *
 * 93. Restore IP Addresses
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(res, s, 0, 0, new ArrayList<>());
        return res;
    }

    private void dfs(List<String> res, String s, int i, int j, List<String> tmp) {
        int len = s.length();
        if (j > 0) {
            // i: inclusive, j: exclusive
            if (i >= len || j > len) {
//                System.out.println("1. terminate: " + tmp);
                return;
            }

            String sub = s.substring(i, j);
//            System.out.println("(" + i + ", " + j + "): \"" + sub + "\"");
            if (valid(sub)) {
                tmp.add(sub);
            } else {
//                System.out.println("2. terminate: " + tmp);
                return;
            }

            if (tmp.size() >= 4) {
                if (tmp.size() == 4 && j == len)
                    res.add(String.join(".", tmp));
//                System.out.println("3. terminate: " + tmp);
                return;
            }
        }

        for (int k = 1; k <= 3; k++) {
            dfs(res, s, j, j+k, new ArrayList<>(tmp));
        }
    }

    private boolean valid(String sub) {
        try {
            int i = Integer.valueOf(sub);
            return i <= 255 && String.valueOf(i).length() == sub.length();
        } catch (Exception e) {
            return false;
        }
    }

    @Test
    public void case1() {
        System.out.println(restoreIpAddresses("25525511135"));
    }

}
