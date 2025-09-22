//Leetcode 3005 Count Elements With Maximum Frequency
// deadez 1ms beats 99.9
class Solution {
    public int maxFrequencyElements(int[] nums) {
        int count = 0; 
        int[] store = new int[101];
        for(int i =0;i<nums.length;i++){
                store[nums[i]]++;
        }
        int max = Integer.MIN_VALUE;
        for(int i = 0;i<101;i++){
            if(max == store[i]) {
                count += store[i];
            }
            if(max<store[i]){
                max = store[i];
                count = store[i];
            }
        }
        return count;
    }
}