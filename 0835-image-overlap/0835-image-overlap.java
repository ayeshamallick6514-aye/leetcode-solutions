class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n= img1.length;
        int maxoverlap = 0;
        for(int rowshift = -(n-1); rowshift<=n-1; rowshift++){
            for(int colshift = -(n-1); colshift<= n-1; colshift++){
                int overlap = 0;
                for(int i=0; i<n; i++){
                    for(int j=0; j<n; j++){
                        int r= i + rowshift;
                        int c= j + colshift;
                        if(r>=0 && r<n && c>=0 && c<n && img1[i][j]==1 && img2[r][c]==1){
                            overlap++;
                        }
                    }
                }
                maxoverlap = Math.max(maxoverlap, overlap);
            }
        }
        return maxoverlap;
    }
}