package _2021.challenge.april;

/**
 * Created by Yiyun On 2021/4/9 07:26
 *
 * 482. License Key Formatting
 */
public class LicenseKeyFormatting {

    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        char[] chars = S.toCharArray();

        int cnt = 0;
        for (int i = chars.length-1; i >= 0; i--) {
            char ch = chars[i];
            if (ch == '-')
                continue;

            cnt++;
            sb.insert(0, Character.toUpperCase(ch));

            if (cnt == K) {
                cnt = 0;
                sb.insert(0, '-');
            }
        }

        String s = sb.toString();
        return (s.length() > 0 && s.charAt(0) == '-') ? s.substring(1, s.length()) : s;
    }

}
