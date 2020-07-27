package twopointers.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2020/5/23 15:45
 * 15. 3Sum
 */
public class ThreeSum {

    public List<List<Integer>> threeSum_review20200727(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);
        int bound = target / 3;
        int n = nums.length;
        for (int i = 0; i < n-2; i++) {
            int num = nums[i];
            if (num > bound)
                break;

            if (i > 0 && num == nums[i-1]) continue;

            for (List<Integer> subRes : twoSum_review20200727(i + 1, nums, target - num)) {
                subRes = new ArrayList<>(subRes);
                subRes.add(num);
                res.add(subRes);
            }
        }
        return res;
    }

    private List<List<Integer>> twoSum_review20200727(int st, int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        int l = st;
        int r = nums.length-1;
        int flag = target / 2;
        while (l < r) {
            int lNum = nums[l];
            if (lNum > flag)
                break;

            if (l != st && lNum == nums[l-1]) {
                l++;
                continue;
            }

            int rNum = nums[r];
            int sum = lNum + rNum;
            if (sum == target) {
                res.add(Arrays.asList(lNum, rNum));
            }

            if (sum > target)
                r--;
            else
                l++;
        }

        return res;
    }




    public List<List<Integer>> threeSum(int[] nums) {
        return threeSum(nums, 0);
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        int flag = target / 3;
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (i > 0 && curr == nums[i-1])
                continue;
            if (curr > flag)
                break;

            twoSum(i+1, nums, target-curr, res, curr);
        }
        return res;
    }

    private List<Integer> twoSum(int st, int[] nums, int target, List<List<Integer>> res, int curr) {
        if (curr == -1)
            System.out.println();

        int l = st;
        int h = nums.length-1;
        int flag = target / 2;
        while (l < h) {
            int lNum = nums[l];
            if (lNum > flag)
                break;
            if (l != st && nums[l] == nums[l-1]) {      // Be careful about removing duplicate results: l != st
                l++;
                continue;
            }

            int hNum = nums[h];
            int sum = lNum + hNum;
            if (sum == target)
                res.add(Arrays.asList(curr, lNum, hNum));

            if (sum > target)
                h--;
            else
                l++;
        }

        return null;
    }

    // HashSet version

    @Test
    public void case1() {
        threeSum(new int[]{-1,0,1,2,-1,-4}).forEach(System.out::println);
    }
}
