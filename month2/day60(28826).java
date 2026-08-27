class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int n = s.length();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int x = target.charAt(i) - 'a';

            if (count[x] > 0) {
                count[x]--;
                ans.append(target.charAt(i));
            } else {
                break;
            }
        }

        for (int i = ans.length() - 1; i >= 0; i--) {
            count[target.charAt(i) - 'a']++;

            int x = target.charAt(i) - 'a';

            for (int c = x + 1; c < 26; c++) {
                if (count[c] > 0) {
                    StringBuilder res = new StringBuilder();

                    res.append(ans.substring(0, i));
                    res.append((char) ('a' + c));
                    count[c]--;

                    for (int j = 0; j < 26; j++) {
                        while (count[j]-- > 0) {
                            res.append((char) ('a' + j));
                        }
                    }

                    return res.toString();
                }
            }
        }

        return "";
    }
}