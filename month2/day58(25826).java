class Solution {
    public int missingMultiple(int[] nums, int k) {
        Arrays.sort(nums);
        int mul = 1;
        for(int i=0;i<nums.length;i++){
            if(k*mul == nums[i]){
                i=0;
                mul++;
            }
        }
        return k*mul;
    }
}