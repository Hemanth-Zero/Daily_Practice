import java.util.*;
class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int i;
        boolean found = true;
        boolean flags[] = new boolean[nums.length];
        Arrays.fill(flags, false);
        for (int j = 0; j < nums.length; j++) {
            nums[j] = ((nums[j] % value) + value) % value;
        }
        for (i = 0; found; i++) {
            found = false;
            int result = i % value;
            for (int j = 0; j < nums.length; j++) {
                if (!flags[j] && nums[j] == result) {
                    flags[j] = true;
                    found = true;
                    break;
                }
            }
        }
        return i - 1;
    }
}
