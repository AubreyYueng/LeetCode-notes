package dynamicprogramming.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2020/3/26 14:38
 * 351. Android Unlock Patterns
 * https://leetcode.com/problems/android-unlock-patterns/
 */
public class AndroidUnlockPatterns {

//    public int numberOfPatterns(int m, int n) {
//
//    }

    @Test
    public void test() {
        List<LinkedList<Integer>> combinations = permute(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        for (LinkedList<Integer> combination : combinations) {
//            System.out.println(Arrays.toString(combination.toArray()));
            int a = 0;
            a += 100*combination.get(0);
            a += 10*combination.get(1);
            a += combination.get(2);

            int b = 0;
            b += 100*combination.get(3);
            b += 10*combination.get(4);
            b += combination.get(5);

            int c = 0;
            c += 100*combination.get(6);
            c += 10*combination.get(7);
            c += combination.get(8);

            if (a + b == c)
                System.out.println(String.format("%s + %s = %s", a, b, c));
        }
    }

    public ArrayList<LinkedList<Integer>> permute(int[] num) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();

        //start from an empty list
        result.add(new LinkedList<>());

        for (int i = 0; i < num.length; i++) {
            //list of list in current iteration of the array num
            ArrayList<LinkedList<Integer>> current = new ArrayList<>();

            for (LinkedList<Integer> l : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < l.size()+1; j++) {
                    // + add num[i] to different locations
                    l.add(j, num[i]);

                    LinkedList<Integer> temp = new LinkedList<Integer>(l);
                    current.add(temp);

                    //System.out.println(temp);

                    // - remove num[i] add
                    l.remove(j);
                }
            }

            result = new ArrayList<LinkedList<Integer>>(current);
        }

        return result;
    }
}
