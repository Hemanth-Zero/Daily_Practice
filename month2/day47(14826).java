class Solution {
    public int maximumLengthSubstring(String s) {
        int[] counts = new int[26]; 
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            int rightCharIndex = s.charAt(right) - 'a';
            counts[rightCharIndex]++;

            while (counts[rightCharIndex] > 2) {
                int leftCharIndex = s.charAt(left) - 'a';
                counts[leftCharIndex]--;
                left++; // Move left pointer forward
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
