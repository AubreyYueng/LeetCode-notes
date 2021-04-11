package _2021.challenge.april;

/**
 * Created by Yiyun On 2021/4/11 17:30
 *
 * 775. Global and Local Inversions
 */
public class GlobalLocalInversions {

    public boolean isIdealPermutation(int[] nums) {
        if (nums.length <= 2) return true;

        int preMax = nums[0];
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] < preMax)
                return false;
            if (nums[i-1] > preMax) {
                preMax = nums[i-1];
                // System.out.println(prePeak);
            }

        }
        return true;
    }

}
