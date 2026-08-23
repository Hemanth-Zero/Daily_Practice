class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0, rightSum = 0;
        int leftQ = 0, rightQ = 0;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                if (i < n / 2) leftQ++;
                else rightQ++;
            } else {
                if (i < n / 2) leftSum += c - '0';
                else rightSum += c - '0';
            }
        }

        if ((leftQ + rightQ) % 2 == 1) return true;

        return leftSum - rightSum != (rightQ - leftQ) / 2 * 9;
    }
}