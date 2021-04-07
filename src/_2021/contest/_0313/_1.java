package _2021.contest._0313;

/**
 * Created by Yiyun On 2021/3/13 22:37
 * Check if One String Swap Can Make Strings Equal
 */
public class _1 {

    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;

        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                String newS1 = replace(i, c2, s1);
                for (int j = i+1; j < s1.length(); j++) {
                    if (s1.charAt(j) == c2) {
                        if (replace(j, c1, newS1).equals(s2))
                            return true;
                    }
                }
                break;
            }
        }
        return s1.equals(s2);
    }

    String replace(int i, char ch, String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(i, ch);
        return sb.toString();
    }

}
