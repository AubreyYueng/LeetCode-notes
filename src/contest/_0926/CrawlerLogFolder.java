package contest._0926;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yiyun On 2020/9/26 23:11
 *
 * 1598. Crawler Log Folder
 */
public class CrawlerLogFolder {

    public int minOperations(String[] logs) {
        LinkedList<String> list = new LinkedList<>();
        for (String log : logs) {
            if (log.equals("../")) {
                if (!list.isEmpty()) list.pop();
            } else if (!log.equals("./"))
                list.push(log);
        }

        return list.size();
    }

    @Test
    public void case1()  {
        assertEquals(2, minOperations(new String[]{
                "d1/","d2/","../","d21/","./"
        }));
    }

    @Test
    public void case2()  {
        assertEquals(3, minOperations(new String[]{
                "d1/","d2/","./","d3/","../","d31/"
        }));
    }

}
