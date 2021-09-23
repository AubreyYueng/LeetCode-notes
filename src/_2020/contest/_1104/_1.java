package _2020.contest._1104;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yiyun On 2020/11/14 21:53
 */
public class _1 {

    public static class OrderedStream {
        private String[] stream;
        private int ptr;
        private int n;

        public OrderedStream() {}


        public OrderedStream(int n) {
            this.stream = new String[n];
            this.ptr = 0;
            this.n = n;
        }

        public List<String> insert(int id, String value) {
            id--;
            List<String> res = new LinkedList<>();
            if (id >= n)
                return res;

            this.stream[id] = value;
            if (ptr >= n || stream[ptr] == null)
                return res;

            res.add(stream[ptr]);
            for (int i = ptr+1; i < n; i++) {
                String v = stream[i];
                if (v != null)
                    res.add(v);
                else {
                    ptr = i;
                    return res;
                }

            }

            return res;
        }
    }

    @Test
    public void case1() {
        // [3,"ccccc"],[1,"aaaaa"],[2,"bbbbb"],[5,"eeeee"],[4,"ddddd"]
        OrderedStream os = new OrderedStream(5);
        os.insert(3, "ccccc");
        os.insert(1, "aaaaa");
        os.insert(2, "bbbbb");
        os.insert(5, "eeeee");
        os.insert(4, "ddddd");
    }

}
