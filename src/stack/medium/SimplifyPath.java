package stack.medium;

import java.util.LinkedList;

/**
 * Created by Yiyun On 2020/6/30 23:04
 *
 * 71. Simplify Path
 */
public class SimplifyPath {

    public String simplifyPath(String path) {
        LinkedList<String> stack =  new LinkedList<>();
        for (String dir : path.split("/+")) {
            if (".".equals(dir) || dir.isEmpty())      // still could be empty, eg. /home/
                continue;

            if ("..".equals(dir)) {
                // Don't merge these two if!!! Or stack is likely to push ".."
                if (!stack.isEmpty())
                    stack.pop();
            } else
                stack.push(dir);
        }

        StringBuilder res = new StringBuilder();
        for (String dir : stack) res.insert(0, "/" + dir);
        return (res.length() == 0) ? "/" : res.toString();
    }

}
