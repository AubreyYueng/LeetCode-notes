package bit.manipulation.easy;

/**
 * Created by Yiyun On 2020/7/16 22:47
 *
 * 268. Missing Number
 */
public class MissingNumber {

    // Require: constant extra space.
    // The following codes are mostly copied from LC Solution
    public int missingNumber(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

}
