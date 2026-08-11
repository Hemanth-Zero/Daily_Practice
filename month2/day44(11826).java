class Solution {
    public int missingInteger(int[] nums) {
        int sum =nums[0];
        int max = nums[0];
        boolean flag = false;
        int j =0;
        for( int i =1;i<nums.length ;i++){
            if(!flag && nums[i] == nums[i-1]+1){
                sum+=nums[i];
            }else{ 
                flag = true;
                j=i;
            }
        }
        while(true){
            boolean flag2 = false;
            for(int i=0;i<nums.length;i++){
                if(nums[i] == sum){
                    flag2 = true;
                }
            }
            if(!flag2) return sum;
            sum++;
        }
        
    }
}