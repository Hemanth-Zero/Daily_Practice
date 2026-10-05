import java.util.Stack;

class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(-1);
            } else {
                int sum = 0;
                while (!stack.isEmpty() && stack.peek() != -1) {
                    sum += stack.pop();
                }
                stack.pop();
                int currentScore = (sum == 0) ? 1 : 2 * sum;
                
                stack.push(currentScore);
            }
        }
        int finalScore = 0;
        while (!stack.isEmpty()) {
            finalScore += stack.pop();
        }
        
        return finalScore;
    }
}
