package array.easy;

/**
 * Created by Yiyun On 2020/7/25 18:59
 *
 * 463. Island Perimeter
 */
public class IslandPerimeter {

    // codes are basically copied from LC Solution
    // Approach:
    // curr id land cell ? res+=4
    // curr's UP is land cell ? res-=2
    // curr's LEFT is land cell? res-=2
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int result = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    result += 4;

                    if (r > 0 && grid[r-1][c] == 1) {
                        result -= 2;
                    }

                    if (c > 0 && grid[r][c-1] == 1) {
                        result -= 2;
                    }
                }
            }
        }
        return result;
    }

}
