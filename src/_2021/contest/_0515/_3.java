package _2021.contest._0515;

/**
 * Created by Yiyun On 2021/5/15 10:55
 */
public class _3 {

    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;
        char[][] res = new char[n][m];
        for (int i = 0; i < m; i++) {
            int fall = 0;
            for (int j = n-1; j >= 0; j--) {
                char ch = box[i][j];
                if (ch == '.')
                    fall++;
                else if (ch == '#')
                    res[j+fall][m-i+1] = '#';
                else {
                    fall = 0;
                    res[j][m-i+1] = '*';
                }
            }
        }
        return res;
    }
}
