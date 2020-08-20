package array.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 8/18/20 22:24
 *
 * 118. Pascal's Triangle
 */
public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) return Collections.emptyList();
        List<List<Integer>> res = new LinkedList<>();
        res.add(Collections.singletonList(1));

        List<Integer> curr = res.get(0);
        for (int i = 1; i < numRows; i++) {
            List<Integer> next = genNext(curr);
            res.add(next);
            curr = next;
        }
        return res;
    }

    private List<Integer> genNext(List<Integer> curr) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < curr.size(); i++) {
            res.add(curr.get(i)+curr.get(i-1));
        }
        res.add(0, curr.get(0));
        res.add(curr.get(curr.size()-1));
        return res;
    }

}
