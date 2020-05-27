package twopointers.easy;

/**
 * Created by Yiyun On 2020/5/26 21:32
 *
 * 167. Two Sum II - Input array is sorted
 */
public class TwoSumII {

    public int[] twoSum(int[] numbers, int target) {
        int st = 0;
        int ed = numbers.length-1;
        while (st < ed) {
            int tmpSum = numbers[st] + numbers[ed];
            if (tmpSum == target)
                return new int[]{st+1, ed+1};
            if (tmpSum > target)
                ed--;
            else
                st++;
        }
        return new int[]{-1, -1};
    }

}
