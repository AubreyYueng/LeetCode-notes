package string.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/8/29 18:05
 *
 * 165. Compare Version Numbers
 *
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 *
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the
 * second first-level revision.
 *
 * You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4
 * has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision
 * number are both 0.

 * Example 1:
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1

 * Example 2:
 * Input: version1 = "1.0.1", version2 = "1"
 * Output: 1
 *
 * Example 3:
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 *
 * Example 4:
 * Input: version1 = "1.01", version2 = "1.001"
 * Output: 0
 * Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
 *
 * Example 5:
 * Input: version1 = "1.0", version2 = "1.0.0"
 * Output: 0
 * Explanation: The first version number does not have a third level revision number, which means its third level revision number is default to "0"
 *
 * Note:
 * Version strings are composed of numeric strings separated by dots . and this numeric strings may have leading zeroes.
 * Version strings do not start or end with dots, and they will not be two consecutive dots.
 *
 * TODO: NOTICE'.0.n'(n>0); Method of compareVersion_simple has the similar thought as completing the shorter list
 */
public class CompareVersionNumbers {

    public int compareVersion(String version1, String version2) {
        List<Integer> ints1 = Arrays.stream(version1.split("\\.")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> ints2 = Arrays.stream(version2.split("\\.")).map(Integer::parseInt).collect(Collectors.toList());

        int size1 = ints1.size();
        int size2 = ints2.size();
        int i = 0;

        for (; i < size1; i++) {
            if (i == size2) {
                break;
            }
            int i1 = ints1.get(i);
            int i2 = ints2.get(i);

            if (i1 > i2)
                return 1;
            if (i1 < i2)
                return -1;
        }

        if (size1 == size2) return 0;

        boolean ints1Longer = size1 > size2;
        List<Integer> longer = ints1Longer ? ints1 : ints2;
        for (int j = i; j < longer.size(); j++) {
            if (longer.get(j) > 0)
                return ints1Longer ? 1 : -1;
        }
        return 0;
    }

    public int compareVersion_simple(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int len = Math.max(nums1.length, nums2.length);
        for(int i = 0; i < len; i++){
            int v1 = i < nums1.length ? Integer.parseInt(nums1[i]) : 0;
            int v2 = i < nums2.length ? Integer.parseInt(nums2[i]) : 0;
            if(v1 > v2) return 1;
            if(v1 < v2) return -1;
        }
        return 0;
    }

    @Test
    public void case1() {
        assertEquals(-1, compareVersion("0.1", "1.1"));
    }

    @Test
    public void case2() {
        assertEquals(1, compareVersion("1.0.1", "1"));
    }

    @Test
    public void case3() {
        assertEquals(-1, compareVersion("7.5.2.4", "7.5.3"));
    }

    @Test
    public void case4() {
        assertEquals(0, compareVersion("1.01", "1.001"));
    }

    @Test
    public void case5() {
        assertEquals(0, compareVersion("1.0", "1.0.0"));
    }

    @Test
    public void case6() {
        assertEquals(-1, compareVersion("1", "1.1"));
    }
}
