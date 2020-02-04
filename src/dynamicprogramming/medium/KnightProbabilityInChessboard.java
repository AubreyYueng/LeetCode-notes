package dynamicprogramming.medium;

/**
 * Created by Yiyun On 2020/2/3 17:50
 *
 * 688. Knight Probability in Chessboard
 * https://leetcode.com/problems/knight-probability-in-chessboard/
 */
public class KnightProbabilityInChessboard {

    public double knightProbability(int N, int K, int r, int c) {
        double[][] res = new double[N][N];
        res[r][c] = 1;
        int[] dr = new int[]{2, 2, 1, 1, -1, -1, -2, -2};
        int[] dc = new int[]{1, -1, 2, -2, 2, -2, 1, -1};

        for (int k = 0; k < K; k++) {
            double[][] currRes = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int d = 0; d < 8; d++) {
                        int currR = i+dr[d];
                        int currC = j+dc[d];
                        if(currR>=0&&currR<N&&currC>=0&&currC<N){
                            // When long won't work, consider using double and cal by prob
                            currRes[currR][currC]+=res[i][j]/8.0;
                        }
                    }
                }
            }
            res = currRes;
        }

        double ans = 0;
        for(double[] arr: res) {
            for (double a: arr)
                ans+=a;
        }

        return ans;
    }

}
