import java.util.*;

class Solution {
    public int distinctSubseqII(String s) {
        int mod = 1000000007;
        int[] dp = new int[s.length() + 1];
        int[] last = new int[26];
        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            int c = s.charAt(i - 1) - 'a';
            dp[i] = (2 * dp[i - 1]) % mod;
            dp[i] = (dp[i] - last[c] + mod) % mod;
            last[c] = dp[i - 1];
        }
        return (dp[s.length()] - 1 + mod) % mod;
    }
}