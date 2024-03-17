import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MaxIslandCenter {
    private static final int[] ROW_OFFSETS = {-1, 0, 0, 1};
    private static final int[] COL_OFFSETS = {0, -1, 1, 0};

    public Set<Point> maxIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int maxIslandSize = 0;
        Set<Point> maxIsland = new HashSet<>();

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    Set<Point> island = new HashSet<>();
                    int islandSize = DFS(grid, i, j, visited, island);
                    if (islandSize > maxIslandSize) {
                        maxIslandSize = islandSize;
                        maxIsland = island;
                    }
                }
            }
        }
        return maxIsland;
    }

    public int[] calculateCenter(Set<Point> island) {
        int sumRow = 0, sumCol = 0;
        for (Point cell : island) {
            sumRow += cell.x;
            sumCol += cell.y;
        }
        return new int[]{sumRow / island.size(), sumCol / island.size()};
    }

    private boolean isSafe(int[][] grid, int row, int col, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;
        return (row >= 0 && row < rows && col >= 0 && col < cols && grid[row][col] == 1 && !visited[row][col]);
    }

    private int DFS(int[][] grid, int startRow, int startCol, boolean[][] visited, Set<Point> island) {
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(startRow, startCol));
        visited[startRow][startCol] = true;
        int islandSize = 0;

        while (!stack.isEmpty()) {
            Point current = stack.pop();
            int row = current.x;
            int col = current.y;
            islandSize++;

            island.add(current);

            for (int i = 0; i < 4; ++i) {
                int newRow = row + ROW_OFFSETS[i];
                int newCol = col + COL_OFFSETS[i];
                if (isSafe(grid, newRow, newCol, visited)) {
                    stack.push(new Point(newRow, newCol));
                    visited[newRow][newCol] = true;
                }
            }
        }

        return islandSize;
    }
}
