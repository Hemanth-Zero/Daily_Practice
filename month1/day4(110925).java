import java.util.*;

class Solution {

    // Check if a character is a vowel
    public boolean isVowel(char s){
        s = Character.toLowerCase(s);
        return (s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u');
    }

    public String sortVowels(String s) {
        // Use ArrayList to preserve duplicates
        List<Character> vowels = new ArrayList<>();

        // Collect vowels from the string
        for (char c : s.toCharArray()) {
            if (isVowel(c)) {
                vowels.add(c);
            }
        }

        // Sort the vowels (ASCII order)
        Collections.sort(vowels);

        // Replace vowels in original string with sorted vowels
        char[] arr = s.toCharArray();
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (isVowel(arr[i])) {
                arr[i] = vowels.get(index);
                index++;
            }
        }

        return new String(arr);
    }

    // Test
    public static void main(String[] args) {
        Solution sol = new Solution();
        String result = sol.sortVowels("lEetcOde");
        System.out.println(result); // Output: "lEOtcede"
    }
}
