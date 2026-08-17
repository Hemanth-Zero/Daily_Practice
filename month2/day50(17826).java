class Solution {
    int[][] memo;

    public int value(int[] dp, int x, int y) {
        if (x == 0) {
            return dp[y];
        }
        return dp[y] - dp[x - 1];
    }

    public int sol(int[] dp, int s, int e) {
        if (s >= e) return 0;

        if (memo[s][e] != -1) {
            return memo[s][e];
        }

        int ans = 0;

        for (int m = s; m < e; m++) {

            int larr = value(dp, s, m);
            int rarr = value(dp, m + 1, e);

            if (larr < rarr) {
                ans = Math.max(
                    ans,
                    larr + sol(dp, s, m)
                );
            }

            else if (larr > rarr) {
                ans = Math.max(
                    ans,
                    rarr + sol(dp, m + 1, e)
                );
            }

            else {
                ans = Math.max(
                    ans,
                    Math.max(
                        larr + sol(dp, s, m),
                        rarr + sol(dp, m + 1, e)
                    )
                );
            }
        }

        return memo[s][e] = ans;
    }

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        int[] dp = new int[n];

        dp[0] = stoneValue[0];

        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + stoneValue[i];
        }

        memo = new int[n][n];

        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memo[i], -1);
        }

        return sol(dp, 0, n - 1);
    }
}