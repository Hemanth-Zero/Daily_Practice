class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: Compute prefix sums in-place to calculate stone group values
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // Step 2: Game theoretic backward induction (DP)
        // Base case: If the player is forced to take all remaining elements at the end
        int maxDifference = stones[n - 1];
        
        // Traverse backwards from the second-to-last index to the second index (x > 1)
        for (int i = n - 2; i >= 1; i--) {
            maxDifference = Math.max(maxDifference, stones[i] - maxDifference);
        }
        
        return maxDifference;
    }
}
