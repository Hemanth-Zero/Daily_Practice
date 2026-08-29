class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        // A palindrome can have at most one odd-frequency character
        int odd = 0;
        char middle = '\0';

        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = n / 2;

        // Frequency of characters available for the first half
        int[] halfCnt = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

        String targetHalf = target.substring(0, halfLen);

        /*
         * First try to make the first half exactly equal
         * to target's first half.
         */
        String equalHalf = buildEqualHalf(targetHalf, halfCnt);

        if (equalHalf != null) {
            String palindrome = makePalindrome(equalHalf, middle);

            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }
        }

        /*
         * Equal half is not enough.
         * Find the smallest half strictly greater than targetHalf.
         */
        String greaterHalf = findGreaterHalf(targetHalf, halfCnt);

        if (greaterHalf == null) {
            return "";
        }

        return makePalindrome(greaterHalf, middle);
    }

    private String buildEqualHalf(String targetHalf, int[] cnt) {
        int[] remaining = cnt.clone();
        StringBuilder sb = new StringBuilder();

        for (char c : targetHalf.toCharArray()) {
            int x = c - 'a';

            if (remaining[x] == 0) {
                return null;
            }

            remaining[x]--;
            sb.append(c);
        }

        return sb.toString();
    }

    private String findGreaterHalf(String targetHalf, int[] cnt) {
        int n = targetHalf.length();

        /*
         * Try changing the rightmost possible position.
         * This gives the smallest lexicographically greater string.
         */
        for (int i = n - 1; i >= 0; i--) {

            int[] remaining = cnt.clone();

            // Keep prefix [0 ... i-1] equal to target
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = targetHalf.charAt(j) - 'a';

                if (remaining[x] == 0) {
                    possible = false;
                    break;
                }

                remaining[x]--;
            }

            if (!possible) {
                continue;
            }

            int current = targetHalf.charAt(i) - 'a';

            // Put the smallest possible character greater than target[i]
            for (int c = current + 1; c < 26; c++) {

                if (remaining[c] == 0) {
                    continue;
                }

                remaining[c]--;

                StringBuilder result = new StringBuilder();

                // Prefix
                for (int j = 0; j < i; j++) {
                    result.append(targetHalf.charAt(j));
                }

                // Greater character
                result.append((char) ('a' + c));

                // Fill remaining positions with smallest characters
                for (int x = 0; x < 26; x++) {
                    for (int k = 0; k < remaining[x]; k++) {
                        result.append((char) ('a' + x));
                    }
                }

                return result.toString();
            }
        }

        return null;
    }

    private String makePalindrome(String half, char middle) {
        StringBuilder result = new StringBuilder();

        result.append(half);

        if (middle != '\0') {
            result.append(middle);
        }

        for (int i = half.length() - 1; i >= 0; i--) {
            result.append(half.charAt(i));
        }

        return result.toString();
    }
}