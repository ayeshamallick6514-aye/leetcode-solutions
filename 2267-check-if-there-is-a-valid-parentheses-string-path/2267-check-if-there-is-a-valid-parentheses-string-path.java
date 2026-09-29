class Solution {

    int m, n;
    Boolean[][][] dp;
    char[][] grid;

    public boolean hasValidPath(char[][] grid) {

        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

       
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

       
        if (grid[0][0] == ')') {
            return false;
        }

        dp = new Boolean[m][n][m + n];

        return dfs(0, 0, 1);
    }

    private boolean dfs(int r, int c, int balance) {

        
        if (balance < 0) {
            return false;
        }

        
        if (balance > (m - r) + (n - c) - 1) {
            return false;
        }

       
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        if (dp[r][c][balance] != null) {
            return dp[r][c][balance];
        }

        
        if (r + 1 < m) {

            int nextBalance = balance;

            if (grid[r + 1][c] == '(') {
                nextBalance++;
            } else {
                nextBalance--;
            }

            if (dfs(r + 1, c, nextBalance)) {
                return dp[r][c][balance] = true;
            }
        }

        
        if (c + 1 < n) {

            int nextBalance = balance;

            if (grid[r][c + 1] == '(') {
                nextBalance++;
            } else {
                nextBalance--;
            }

            if (dfs(r, c + 1, nextBalance)) {
                return dp[r][c][balance] = true;
            }
        }

        return dp[r][c][balance] = false;
    }
}