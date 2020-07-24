package random.medium;

/**
 * Created by Yiyun On 2020/7/23 21:45
 *
 * 470. Implement Rand10() Using Rand7()
 */
public class ImplementRand10UsingRand7 {

    // Codes are learnt from LC Solution
    public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row-1) * 7;
        } while (idx > 40);
        return 1 + (idx - 1) % 10;
    }

    private int rand7() {
        return 0;
    }

}
