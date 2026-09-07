class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        long MOD = 1000000007;
        long dp0= 0;
        long dp1= 0;
        boolean haszero = false;
        for(char ch : binary.toCharArray()){
            if(ch== '0'){
                haszero= true;
                dp0 = (dp0 + dp1) % MOD;
            }
            else{
                dp1 = (dp1 + dp0 + 1) % MOD;
            }
        }
        long ans = (dp0 + dp1) % MOD;
        if(haszero){
            ans = (ans+1) % MOD;
        }
        return (int) ans;
    }
}