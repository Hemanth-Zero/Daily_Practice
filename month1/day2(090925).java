class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = 1_000_000_007;
        long[] dp = new long[n + 1];     // dp[i] = # of people who learn secret on day i
        long[] pref = new long[n + 1];   // prefix sum of dp
        dp[1] = 1;
        pref[1] = 1;

        for (int i = 2; i <= n; i++) {
            int leftIdx = i - delay;    // earliest day whose sharers affect day i
            int rightIdx = i - forget;  // day whose sharers forgot by day i

            long leftSum = 0;
            if (leftIdx >= 1) leftSum = pref[leftIdx];

            long rightSum = 0;
            if (rightIdx >= 1) rightSum = pref[rightIdx];

            dp[i] = (leftSum - rightSum + MOD) % MOD;
            pref[i] = (pref[i - 1] + dp[i]) % MOD;
        }

        int startAlive = Math.max(0, n - forget);
        long ans = (pref[n] - (startAlive >= 1 ? pref[startAlive] : 0) + MOD) % MOD;
        return (int) ans;
    }
}
