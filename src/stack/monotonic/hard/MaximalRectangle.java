package stack.monotonic.hard;

import java.util.LinkedList;

/**
 * Created by Yiyun On 2020/6/4 00:56
 *
 * 85. Maximal Rectangle
 */
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return 0;

        int colCnt = matrix[0].length;
        int maxArea = 0;
        int[] heights = new int[colCnt];
        for (char[] row : matrix) {
            for (int i = 0; i < colCnt; i++) {
                if (row[i] == '0')
                    heights[i] = 0;
                else
                    heights[i]++;
            }
            maxArea = Math.max(maxArea,largestRectangle(heights));
        }
        return maxArea;
    }

    // review 84. Largest Rectangle in Histogram
    private int largestRectangle(int[] histogram) {
        LinkedList<Integer> stack = new LinkedList<>();
        int bottom = -1;
        stack.push(bottom);

        int len = histogram.length;
        int maxArea = histogram[0];     // don't set 0
        for (int i = 0; i < len; i++) {
            int cur = histogram[i];
            while (stack.peek() != bottom && histogram[stack.peek()] > cur) {
                int height = histogram[stack.pop()];
                int width = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        while (stack.peek() != bottom) {
            maxArea = Math.max(maxArea, histogram[stack.pop()] * (len-stack.peek()-1));
        }

        return maxArea;
    }

}
