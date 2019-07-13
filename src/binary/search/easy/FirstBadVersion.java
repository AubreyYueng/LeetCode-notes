package binary.search.easy;

import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/7/13 13:00
 *
 * 278. First Bad Version
 *
 * You are a product manager and currently leading a team to develop a new product. Unfortunately,
 * the latest version of your product fails the quality check. Since each version is developed based on the previous
 * version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the
 * following ones to be bad.

 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.

 * Example:
 * Given n = 5, and version = 4 is the first bad version.
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 *
 */
public class FirstBadVersion {

    /**
     * The isBadVersion API is defined in the parent class VersionControl.
     * boolean isBadVersion(int version);
     */
    public static class Solution extends VersionControl {

        public static int firstBadVersion(int n) {
            long low = 1;
            long high = n;
            while (low < high) {
                long mid = (low + high) / 2;
                out.println("low: " + low + ", high: " + high + ", mid: " + mid);
                if (isBadVersion((int)mid))
                    high = mid;
                else
                    low = mid + 1;
            }
//            out.println("low: " + low + ", high: " + high);
            return (int)low;
        }
    }

    public static class VersionControl {
        static boolean isBadVersion(int version) {
            if (version < 1702766719)
                return false;
            return true;
        }
    }

    @Test
    public void case1() {
        assertEquals(1702766719, Solution.firstBadVersion(2126753390));
    }
}
