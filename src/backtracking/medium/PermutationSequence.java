package backtracking.medium;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/7/11 12:52
 *
 * 60. Permutation Sequence
 */
public class PermutationSequence {

    // The following codes are mostly copied from the LC solution
    // This question uses the thoughts of "Factorial Number System":
    // The greatest number that could be represented with n digits would be n!-1=\sum_{i=0}^{n-1} i*i!,
    // and there're n! permutations from 0 to n!-1.
    public String getPermutation(int n, int k) {
        List<Integer> indices = new LinkedList<>();
        for (int i = 1; i <= n; i++) indices.add(i);

        // computer factorial representation of k
        LinkedList<Integer> factorials = new LinkedList<>();
        int i = 1;
        k-=1;
        while (k != 0) {
            int tmp = k / i;
            factorials.push(k-tmp*i++);
            k = tmp;
            n--;
        }
        // to process zeros on the top of factorial number
        while (n-- > 0) factorials.push(0);

        StringBuilder sb = new StringBuilder();
        while (!factorials.isEmpty()) {
            int factorialDigit = factorials.pop();
            sb.append(indices.remove(factorialDigit));
        }

        return sb.toString();
    }

    @Test
    public void case1() {
        assertEquals("213", getPermutation(3, 3));
    }

    @Test
    public void case2() {
        assertEquals("123", getPermutation(3, 1));
    }

}
