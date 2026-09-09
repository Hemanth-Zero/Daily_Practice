class Solution {
    public long countCommas(long n) {
        long i=1000;
        long t=n-i;
        long count=0;
        while(t>=0){
            count+=t+1;
            i*=1000;
            t=n-i;
        }
        return count;
    }
}