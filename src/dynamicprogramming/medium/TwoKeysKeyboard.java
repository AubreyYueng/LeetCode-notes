package dynamicprogramming.medium;

/**
 * Created by Yiyun On 2020/1/31 21:11
 *
 * 650. 2 Keys Keyboard
 */
public class TwoKeysKeyboard {

    public int minSteps(int n) {
        // the number of A = multiplication of steps of each CP... group
        // math problem: p+q <= pq,
        // means we have to split whenever pq is composite
        // => evaluate the summation of prime factors

        // KEY: try to write down a sample sequence of steps in 'CP' signature, the find the regulation.
        int res = 0;
        int d = 2;
        while (n > 1) {
            while (n % d == 0) {
                res += d;
                n /= d;
            }
            d++;
        }
        return res;
    }
}
