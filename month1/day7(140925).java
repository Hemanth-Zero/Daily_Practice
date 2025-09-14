import java.util.*;
//Leet code no 966. Vowel Spellchecker
//Runtime was 51ms beass 27% 
class Solution {
    private String devowel(String word) {
        return word.replaceAll("[aeiou]", "*");
    }
    public String[] spellchecker(String[] wordlist, String[] queries) {
         //we intialized some coolection to store the 3 diff case 
        Set<String> exact = new HashSet<>();
        Map<String, String> caseInsensitive = new HashMap<>();
        Map<String, String> vowelInsensitive = new HashMap<>();

        //we stored each word in diff case 
        for(String word : wordlist){
            exact.add(word);

            String lower = word.toLowerCase();
            caseInsensitive.putIfAbsent(lower, word);
            //incase the word is not there we add it to case insensitive map

            String devoweled = devowel(lower);
            vowelInsensitive.putIfAbsent(devoweled, word);

        }
        String[] ans = new String[queries.length];
         for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            if (exact.contains(q)) {
                ans[i] = q;
            } else {
                String lower = q.toLowerCase();
                if (caseInsensitive.containsKey(lower)) {
                    ans[i] = caseInsensitive.get(lower);
                } else {
                    String devoweled = devowel(lower);
                    ans[i] = vowelInsensitive.getOrDefault(devoweled, "");
                }
            }
        }    
        return ans;
    }
}