//1935. Maximum Number of Words You Can Type
//so ez ,4ms beats 27%

class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        int count = 0;
        boolean flag = true;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == ' ') {
                if (flag) {
                    count++;
                }
               
                flag = true;
            } else {
                for (int j = 0; j < brokenLetters.length() && flag; j++) {
                    if (brokenLetters.charAt(j) == c) {
                        flag = false;
                    }
                }
            }
        }
        if (flag) count++;

        return count;
    }
}