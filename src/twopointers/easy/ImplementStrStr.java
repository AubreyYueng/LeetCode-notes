package twopointers.easy;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/4 00:06
 *
 * 28. Implement strStr()
 */
public class ImplementStrStr {

    // Here we can operate in constant time using Rabin-Karp
    // The following codes are mostly copied from LC solution
    public int strStr(String haystack, String needle) {
        int l = needle.length();
        int n = haystack.length();

        if (l > n) return -1;

        // base value for rolling hash function
        int base = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long)Math.pow(2, 31);

        // compute the hash of strings haystack[:l], needle[:l]
        long currHash = hashOfStr(base, haystack, l, modulus);
        long expectedHash = hashOfStr(base, needle, l, modulus);

        if (currHash == expectedHash) return 0;

        // eg., for a sub-string of length 3
        // hash(1, 3) = [1]base^2 + [2]base^1 + [3]base^0
        // hash(2, 4) =             [2]base^2 + [3]base^1 + [4]base^0
        // Thus, hash(2, 4) = (hash(1, 3)base - [1]base^3) + [4]base^0
        // Remember!!!! Don't compute hash(2, 4) as: (hash(1, 3)-[1]base^2) * base + [4]base^0

        // the coefficient of the to-be-removed number(the first digit)
        long removedCoefficient = 1;
        for (int i = 0; i < l; i++) {
            removedCoefficient = (removedCoefficient * base) % modulus;
        }

        for (int i = 0; i < n - l; i++) {
            int newChar2Int = char2Int(i+l, haystack);
            int oldChar2Int = char2Int(i, haystack);
            currHash = ((currHash * base - oldChar2Int * removedCoefficient) + newChar2Int) % modulus;

            if (currHash == expectedHash)
                return i+1;
        }

        return -1;
    }

    private long hashOfStr(int base, String s, int len, long modulus) {
        long hash = 0;
        for (int i = 0; i < len; i++) {
            int char2Int = char2Int(i, s);
            hash = hashWithChar(hash, base, char2Int, modulus);
        }
        return hash;
    }

    private long hashWithChar(long prevHash, int base, int char2Int, long modulus) {
        return (prevHash * base + char2Int) % modulus;
    }

    private int char2Int(int i, String s) {
        return (int)s.charAt(i) - (int)'a';
    }

    @Test
    public void case1() {
        assertEquals(107, strStr(
                "baabbaaaaaaabbaaaaabbabbababaabbabbbbbabbabbbbbbabababaabbbbbaaabbbbabaababababbbaabbbbaaabbaababbbaabaabbabbaaaabababaaabbabbababbabbaaabbbbabbbbabbabbaabbbaa",
                "bbaaaababa"));
    }

}
