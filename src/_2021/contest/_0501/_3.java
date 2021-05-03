package _2021.contest._0501;

import java.util.Arrays;

/**
 * Created by Yiyun On 2021/5/1 11:10
 */
public class _3 {

    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 0; i < arr.length-1; i++) {
            int a = arr[i];
            int b = arr[i+1];
            if (b <= a+1)
                continue;
            arr[i+1] = arr[i] + 1;
        }
        return arr[arr.length-1];
    }
}
