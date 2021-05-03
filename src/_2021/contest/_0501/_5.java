package _2021.contest._0501;

/**
 * Created by Yiyun On 2021/5/1 22:33
 */
public class _5 {

    public int getMinDistance(int[] nums, int target, int start) {
        Integer l = null;
        for (int i = start; i >= 0; i--) {
            if(nums[i] == target) {
                l = i;
                break;
            }
        }
        Integer r = null;
        for (int i = start; i < nums.length; i++) {
            if(nums[i] == target) {
                r = i;
                break;
            }
        }
        if (l == null || r == null)
            return l != null ? Math.abs(l-start) : Math.abs(r-start);
        return Math.abs(l-start) < Math.abs(r-start) ? Math.abs(l-start) : Math.abs(r-start);
    }
}
