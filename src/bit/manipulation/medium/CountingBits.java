package bit.manipulation.medium;

/**
 * Created by Yiyun On 2020/6/13 22:20
 * 338. Counting Bits
 */
public class CountingBits {

    // every [2^n, 2^{n+1}-1], i.e.: in set of { 2^n + i | i < 2^n }
    // add a 1 at the beginning of every i we get 2^n+i
    // we have [2^n + 0] = 1 = [0] + 1; [2^n + i] = [i] + 1
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        int b = 1;      // b for 2^n
        while (b <= num) {
            int i = 0;
            while (i < b && b+i <= num) {
                res[b+i] = res[i] + 1;
                i++;
            }
            b *= 2;
        }
        return res;
    }

}
