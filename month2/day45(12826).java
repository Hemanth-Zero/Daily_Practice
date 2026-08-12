import java.util.HashMap;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            int key = nums[right];
            map.put(key, map.getOrDefault(key, 0) + 1);
            while (map.get(key) > k) {
                int leftKey = nums[left];
                map.put(leftKey, map.get(leftKey) - 1);
                left++; 
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
