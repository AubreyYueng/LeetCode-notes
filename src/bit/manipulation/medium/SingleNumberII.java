package bit.manipulation.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/30 22:26
 *
 * 137. Single Number II
 */
public class SingleNumberII {

    // Q: Given a non-empty array of integers, every element appears three times except for one,
    // which appears exactly once. Find that single one.

    /**
     Suppose we have 2 sets called 'once' and 'twice'.
     1. When we first see a number in the power of 2 after splitting, we put it in 'once'.
     2. If a number has already been in 'once', remove it from 'once' and put it in 'twice'.
     3. If a number has already been in 'twice', remove it from 'twice', and we are done.
     4. After processing all numbers, numbers are only left in 'once' while 'twice' is empty,
     thus we can collect and return all data in 'once'.

     For example: Suppose we have {5, 7, 8, 5, 7, 5, 7} in which 5=1+4, 7=1+2+4, 8=8.
               Once           Twice
     1+4       1 4
     1+2+4     2               1 4
     8         2 8             1 4
     1+4       2 8
     1+2+4     8 1 4           2
     1+4       8               2 1 4
     1+2=4     8
     Thus the answer is 8
     */
    // Codes are copied from LC Solution
    public int singleNumber(int[] nums) {
        int seenOnce = 0, seenTwice = 0;
        for (int num : nums) {
            // A XOR B: remove-from/put-in A if exists/not-exists in B, i.e. (A union B)-(A intersect B)
            // NOT(A) AND B: remove from B if in A, i.e. B-A
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }
        return seenOnce;
    }

    @Test
    public void case1() {
        assertEquals(3, singleNumber(new int[]{2, 2, 3, 2}));
    }

    @Test
    public void case2() {
        assertEquals(99, singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

}
