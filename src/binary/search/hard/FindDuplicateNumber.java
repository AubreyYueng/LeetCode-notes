package binary.search.hard;

import org.junit.Test;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2019/7/14 02:15
 *
 * 287. Find the Duplicate Number
 *
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one.
 *
 * Example 1:
 * Input: [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 * Input: [3,1,3,4,2]
 * Output: 3
 *
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 *
 * TODO: Floyd's Tortoise and Hare (Cycle Detection) : see linkedlist.medium.LinkedListCycleII
 * TODO: index 0, 1, 2, 3, 4, 5, 6  -> 0,1,[3,2,5,6,4,2,5,6...]
 * TODO: value:1, 3, 5, 2, 2, 6, 4  -> 1,3,[2,5,6,4,2,5,6,4...]
 * TODO: step1. Find intersection:
 * TODO:        Slow takes 1 step:1,3,2,5,6...
 * TODO:        Fast takes 2 step:1,2,6,2,6...
 * TODO:        So the intersection is 5;
 * TODO: step2: Find entrance. It's like:
 * TODO:    st==e===i1--e---i2--e (e for entrance, i for intersect, st for start;)
 * TODO:    So Range(st,e) = Range(i1,e)
 *
 */
public class FindDuplicateNumber {

    public int findDuplicate(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
//            out.println("slow: " + tortoise + ", hare: " + hare);
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

//        out.println("slow: " + tortoise + ", hare: " + hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
//            out.println("ptr1: " + ptr1 + ", ptr2: " + ptr2);
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }
//        out.println("ptr1: " + ptr1 + ", ptr2: " + ptr2);
        return ptr1;
    }

    @Test
    public void case1() {
        assertEquals(2, findDuplicate(new int[]{1,3,5,2,2,6,4}));
    }

    @Test
    public void case2() {
        assertEquals(3, findDuplicate(new int[]{3,1,3,4,2}));
    }


}
