package _2021.challenge.march;

/**
 * Created by Yiyun On 2021/3/2 23:55
 */
public class SetMismatch {

    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        for (int n : nums) {
            int i = Math.abs(n);
            if (nums[i-1] < 0)
                res[0] = i;
            else {
                nums[i-1] = -nums[i-1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res[1] = i + 1;
                break;
            }
        }
        return res;
    }

}
