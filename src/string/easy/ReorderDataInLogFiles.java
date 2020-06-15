package string.easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Yiyun On 2020/6/15 13:16
 *
 * 937. Reorder Data in Log Files
 */
public class ReorderDataInLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        List<Helper> helpers = Arrays.stream(logs).map(Helper::new).collect(Collectors.toList());
        List<Helper> digits = helpers.stream().filter(h -> h.digit).collect(Collectors.toList());
        helpers.removeAll(digits);
        helpers.sort(Comparator.comparing((Helper h) -> h.content).thenComparing(h -> h.identifier));
        helpers.addAll(digits);
        return helpers.stream().map(h -> h.log).collect(Collectors.toList()).toArray(new String[]{});
    }

    private static class Helper {
        String log;
        String identifier;
        String content;
        boolean digit;

        private Helper(String log) {
            this.log = log;
            String[] split = log.split(" ", 2);
            identifier = split[0];
            content = split[1];
            digit = Character.isDigit(content.charAt(0));
        }
    }
}
