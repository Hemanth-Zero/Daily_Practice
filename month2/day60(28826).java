class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int n = s.length();
        int matched = 0;

        while (matched < n) {
            int c = target.charAt(matched) - 'a';

            if (count[c] == 0) {
                break;
            }

            count[c]--;
            matched++;
        }

        for (int i = matched; i >= 0; i--) {
            if (i < matched) {
                count[target.charAt(i) - 'a']++;
            }

            if (i == n) {
                continue;
            }

            int x = target.charAt(i) - 'a';

            for (int c = x + 1; c < 26; c++) {
                if (count[c] > 0) {
                    StringBuilder ans = new StringBuilder();

                    ans.append(target, 0, i);
                    ans.append((char) ('a' + c));

                    count[c]--;

                    for (int j = 0; j < 26; j++) {
                        while (count[j] > 0) {
                            ans.append((char) ('a' + j));
                            count[j]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}