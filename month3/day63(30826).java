class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int min=nums[0];
        int max = nums[0];
        int i=0,j=0;
        for(int k=0;k<n;k++){
            int a = nums[k];
            if(a<min){
                min = a;
                i=k;
            }
            if(a>max){
                max = a;
                j=k;
            }
        }
        int in = n-i-1;
        int jn = n-j-1;
        int f = Math.max(i+1,j+1);
        int back = Math.max(in+1,jn+1);
        int mf = i+1+jn+1; 
        int mb = j+1+in+1;
        return Math.min(Math.min(f,back),Math.min(mf,mb));

    }
}