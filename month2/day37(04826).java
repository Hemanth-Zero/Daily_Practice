import java.util.Collections;
class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> ans = new ArrayList<Integer>();
        Arrays.sort(nums);
        int s = nums[0];
        int end = nums[nums.length - 1];

        int i =0;
        while( i<nums.length){
            if(nums[i] != s){
                ans.add(s);
            }else i++;
            s++;
        }
        return ans;
    }
}