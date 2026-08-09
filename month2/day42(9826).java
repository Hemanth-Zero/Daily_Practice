class Solution {
    int[][] dp;
    int[] suffix;

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n];

        // Fill DP with -1
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        // Suffix sum
        suffix[n - 1] = piles[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = piles[i] + suffix[i + 1];
        }

        return solve(0, 1);
    }

    int solve(int i, int M) {

        // All piles are taken
        if (i >= suffix.length) {
            return 0;
        }

        // Already calculated
        if (dp[i][M] != -1) {
            return dp[i][M];
        }

        // If we can take all remaining piles
        if (2 * M >= suffix.length - i) {
            return dp[i][M] = suffix[i];
        }

        int ans = 0;

        // Try taking X piles
        for (int X = 1; X <= 2 * M; X++) {

            int nextM = Math.max(M, X);

            // Current player gets all remaining stones
            // minus what the opponent can optimally get
            int current = suffix[i] - solve(i + X, nextM);

            ans = Math.max(ans, current);
        }

        return dp[i][M] = ans;
    }
}