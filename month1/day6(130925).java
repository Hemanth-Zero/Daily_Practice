class Solution {
    public boolean isvowel(char s) {
        s = Character.toLowerCase(s); // ensure lowercase
        return (s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u');
    }

    public int maxFreqSum(String s) {
        int[] a = new int[26];  // frequency array

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) { // only count letters
                a[Character.toLowerCase(c) - 'a']++;
            }
        }

        int vowelmax = 0;
        int constamax = 0;

        for (int i = 0; i < 26; i++) {
            char ch = (char) ('a' + i);
            if (isvowel(ch)) {
                vowelmax = Math.max(vowelmax, a[i]);
            } else {
                constamax = Math.max(constamax, a[i]);
            }
        }

        return vowelmax + constamax;
    }
}
