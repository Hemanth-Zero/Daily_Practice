class Solution {
    Boolean[] dp;
    public int maxsq(int a){
        int x = (int) Math.sqrt(a);
        return x ;
    }
    public boolean willwin(int n){
        if(n < 1) return false;
        if (dp[n] != null)
            return dp[n];
        
        int ms = maxsq(n);
        for(int i = ms; i > 0; i-- ){
            if( !willwin(n-(i*i)) ){
                dp[n] =true;
                return true;
            }
        }
        dp[n] = false;
        return false;
    }
    public boolean winnerSquareGame(int n) {
        dp = new Boolean[n + 1];
        return willwin(n);
    }
}