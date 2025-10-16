import java.util.*;

class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int[] freq = new int[value];
        for (int num : nums) {
            int rem = ((num % value) + value) % value;
            freq[rem]++;
        }

        int x = 0;
        while (true) {
            int rem = x % value;
            if (freq[rem] == 0) return x;
            freq[rem]--;
            x++;
        }
    }
}
