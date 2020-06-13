package bit.manipulation.easy;

/**
 * Created by Yiyun On 2020/6/13 00:47
 *
 * 169. Majority Element
 */
public class MajorityElement {

    // Boyer-Moore Voting Algorithm
    // Majority: appear times must > n/2
    public int majorityElement(int[] nums) {
        int cnt = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (cnt == 0) {
                // cnt 0 means there's no majority elements in the previous array
                // i.e.: NEITHER the supposed candidate NOR the other element in the previous array is of majority
                candidate = num;
            }

            cnt += candidate==num ? 1 : -1;
        }

        // It's stated in the LC question that the result must exist,
        // otherwise, we will need to check if the returning candidate appears more than n/2 times
        // We can compare it to several voting rounds:
        // in the previous rounds they can't find a majority against the votes, then in end, the returning candidate
        // beats the final round. Also because the LC question suppose majority exist, so the winner in the final round
        // must be the majority, we can't say it is the majority without this suppose.
        return candidate;
    }

}
