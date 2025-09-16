//2197. Replace Non-Coprime Numbers in Array
// i got some logic was unable to get idea of using stack 
//79 ms beats 23%
class Solution {
    int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;  // safer than (a*b)/gcd to reduce overflow risk
    }
    public List<Integer> replaceNonCoprimes(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(nums[0]);
        for(int i = 1;i<n;i++){
             int b = nums[i];
            while (!stack.isEmpty() && gcd(stack.peek(), b) > 1) {
                b = lcm(stack.pop(), b);
            }
            stack.push(b);
        }
        List<Integer> list = new ArrayList<>();
        while(!stack.isEmpty()){
            list.add(stack.pop());
        }
        Collections.reverse(list);
        return list;
    }
}