package array.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2020/6/13 05:45
 *
 * 448. Find All Numbers Disappeared in an Array
 */
public class FindAllNumbersDisappearedInAnArray {

    // in-place query. similar to 41. First Missing Positive
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new LinkedList<>();
        for (int num : nums) {
            int idx = Math.abs(num)-1;
            if (nums[idx] > 0)
                nums[idx] *= -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0)
                res.add(i+1);
        }

        return res;
    }

}
