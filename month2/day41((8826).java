class Solution {
    public int[] validSequence(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // last[i] stores the maximum index in word1 to match word2[i] 
        // such that word2[i...n-1] can be matched with 0 mismatches.
        int[] last = new int[n];
        int p1 = m - 1;
        
        for (int i = n - 1; i >= 0; i--) {
            while (p1 >= 0 && word1.charAt(p1) != word2.charAt(i)) {
                p1--;
            }
            last[i] = p1;
            p1--;
        }
        
        int[] ans = new int[n];
        boolean usedMismatch = false;
        p1 = 0;
        
        for (int i = 0; i < n; i++) {
            boolean matched = false;
            
            while (p1 < m) {
                // Option 1: Direct character match
                if (word1.charAt(p1) == word2.charAt(i)) {
                    ans[i] = p1;
                    p1++;
                    matched = true;
                    break;
                } else {
                    // Option 2: Use mismatch if available and remaining suffix is valid
                    if (!usedMismatch) {
                        if (i == n - 1 || p1 < last[i + 1]) {
                            ans[i] = p1;
                            p1++;
                            usedMismatch = true;
                            matched = true;
                            break;
                        }
                    }
                    // Cannot match at p1, move to next character in word1
                    p1++;
                }
            }
            
            // If we couldn't match word2[i] anywhere, no valid sequence exists
            if (!matched) {
                return new int[0];
            }
        }
        
        return ans;
    }
}