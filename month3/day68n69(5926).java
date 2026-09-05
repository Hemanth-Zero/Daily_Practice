class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] mins = new int[n];
        int[] maxs = new int[n];
        int min =nums[n-1];
        int max =nums[0];
        for(int i=0;i<n;i++){
            max = Math.max(nums[i],max);
            min = Math.min(nums[n-1-i],min);
            maxs[i] = max;
            mins[n-1-i] = min;
        }
        for(int i=0;i<n;i++){
            if(maxs[i]-mins[i] <= k) return i;
        }
        return -1;
    }
}