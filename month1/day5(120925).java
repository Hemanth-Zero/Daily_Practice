//3227. Vowels Game in a String
//ez i got it with 15ms beat 27% 
class Solution {
    int vowel(String str) {
        int count = 0;
        for (char s : str.toCharArray()) {  // need to use toCharArray()
            s = Character.toLowerCase(s);
            if (s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u') {
                count++;
            }
        }
        return count; // you missed return
    }

    public boolean doesAliceWin(String s) {
        int count = vowel(s);
        if (count == 0) return false;
        return true;
    }
}
