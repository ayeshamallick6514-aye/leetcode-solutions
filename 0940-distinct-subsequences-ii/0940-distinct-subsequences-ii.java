class Solution {
    public int distinctSubseqII(String s) {
        long MOD = 1000000007L;
        long[] last = new long[26];
        long total =0;
    
        for(char ch: s.toCharArray()){
            int index = ch - 'a';
            long add = (total+1) % MOD;
            total = (total + add - last[index] + MOD) % MOD;
            last[index] = add;
        }
        return (int) total;
    }
}