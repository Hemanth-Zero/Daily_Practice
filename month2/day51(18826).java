class Solution {
    public int largestInteger(int[] nums, int k) {
        int n= nums.length;
        int[] count = new int[51];
        int nof =0;
        int max = -1;
        for(int i=0;i<n-k+1;i++){
            nof++;
            for(int z=0;z<k;z++){
                count[nums[i+z]]++;
                max = Math.max(max,nums[i+z]);
            }
        }
        if(nof<2) return max;
        for(int i=max;i>-1;i--){
            if(count[i]==1) return i;
        }
        return -1;
    }
}