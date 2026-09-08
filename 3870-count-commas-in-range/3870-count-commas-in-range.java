class Solution {
    public int countCommas(int n) {
        long ans =0;
        if(n>=1000){
            ans += n-999L;
        }
        if(n>=1000000){
            ans += (n-999999L) * 2L;
        }
        if(n>=1000000000){
            ans += (n-999999999L) * 3L;
        }
        return (int) ans;
    }
}