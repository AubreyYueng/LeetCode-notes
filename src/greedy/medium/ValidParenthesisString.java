package greedy.medium;

/**
 * Created by Yiyun On 2020/7/22 20:53
 *
 * 678. Valid Parenthesis String
 */
public class ValidParenthesisString {

    // Codes are referred from the LC Solution
    // lo/hi: the smallest/largest possible number of '(' after processing the current character in the string
    public boolean checkValidString(String s) {
        int lo = 0, hi = 0;
        for (char c : s.toCharArray()) {
            lo += c=='(' ? 1 : -1;
            hi += c!=')' ? 1: -1;
            if (hi < 0) break;
            lo = Math.max(0, lo);
        }
        return lo == 0;
    }

}
