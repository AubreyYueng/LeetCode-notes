package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yiyun On 2020/5/27 18:12
 *
 * 22. Generate Parentheses
 */
public class GenerateParentheses {

    // Approach 2: Backtracking
    private List<String> generateParenthesisBacktrack_copied(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, String cur, int open, int close, int n) {
        if (cur.length() == n * 2) {
            ans.add(cur);
            return;
        }

        if (open < n)
            backtrack(ans, cur+"(", open+1, close, n);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, n);
    }

    // Approach 1: Brute Force
    public List<String> bruteForce_copied(int n) {
        List<String> combinations = new ArrayList<>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }

}
