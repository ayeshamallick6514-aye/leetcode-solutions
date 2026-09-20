class Solution {
    public int reverseDegree(String s) {
        int ans = 0;
        for(int i=0; i<s.length(); i++){
            int reversevalue= 'z' - s.charAt(i) + 1;
            int position = i+1;
            ans += reversevalue * position;
            }
        return ans;    
    }
}