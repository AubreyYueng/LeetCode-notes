package _2021_01;

import java.util.Arrays;

/**
 * Created by Yiyun On 2021/1/1 12:24
 *
 * 266. Palindrome Permutation
 */
public class PalindromePermutation {

    // bitmap sol
    public boolean canPermutePalindrome(String s) {
        int[] flip = new int[128];
        for (char c : s.toCharArray()) {
            flip[c] ^= 1;
        }
        return Arrays.stream(flip).filter(i -> i==1).count() <= 1;
    }

}
