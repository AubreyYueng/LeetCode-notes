package greedy.medium;

/**
 * Created by Yiyun On 2020/7/1 23:32
 *
 * 134. Gas Station
 */
public class GasStation {

    public int canCompleteCircuit_review20200724(int[] gas, int[] cost) {
        int tmp = 0;
        int st = 0;
        int total = 0;
        for (int i = 0; i < cost.length; i++) {
            int curr = gas[i] - cost[i];
            tmp += curr;
            total += curr;
            if (tmp < 0) {
                st = i+1;
                tmp = 0;
            }
        }
        return total < 0 ? -1 : st;
    }

    // If at some station curr_tank is less than 0, mark this station as a new starting point,
    // reset curr_tank to zero. Check total gas at last.
    // Reason: It's not hard to think that, when starts from i, one can go furthest to j, then one can't go further
    // than j if starts from [i, j].
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curr = 0;
        int res = 0;
        int total = 0;

        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            curr += gas[i] - cost[i];
            if (curr < 0) {
                res = i+1;
                curr = 0;
            }
        }

        return total >= 0 ? res : -1;
    }

}
