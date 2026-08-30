class Solution {
    public int minimumDeletions(int[] nums) {
        int minindex=0;
        int maxindex=0;
        for(int i=0; i<nums.length; i++){
            if(nums[i]< nums[minindex]){
                minindex=i;
            }
            if(nums[i] > nums[maxindex]){
                maxindex=i;
            }
        }
        int n= nums.length;
        //both front 
        int front= Math.max(minindex, maxindex) + 1;
        //both back
        int back= n- Math.min(minindex, maxindex);
        //both from front and back
        int mixed= Math.min(minindex, maxindex) + 1 + n- Math.max(minindex, maxindex);

        return Math.min(front, Math.min(back, mixed));
    }
}