import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // Store value + original index
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];   // value
            arr[i][1] = i;         // original index
        }

        // Sort according to value
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int i = 0;

        while (i < n) {

            // Find one group
            int j = i;

            while (j + 1 < n &&
                   arr[j + 1][0] - arr[j][0] <= limit) {
                j++;
            }

            // Get values and indices of this group
            ArrayList<Integer> values = new ArrayList<>();
            ArrayList<Integer> indices = new ArrayList<>();

            for (int k = i; k <= j; k++) {
                values.add(arr[k][0]);
                indices.add(arr[k][1]);
            }

            // Sort original indices
            Collections.sort(indices);

            // Put smallest values at smallest indices
            for (int k = 0; k < values.size(); k++) {
                nums[indices.get(k)] = values.get(k);
            }

            i = j + 1;
        }

        return nums;
    }
}