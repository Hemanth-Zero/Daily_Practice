class Solution {
    public int product(int n){
        int product =1;
        while(n!=0){
            int d = n%10;
            product*= d;
            n = n/10;
        }
        return product;
    }
    public int smallestNumber(int n, int t) {
        while(true){
            if(product(n)%t==0){
                return n;
            }
            n++;
        }
    }
}