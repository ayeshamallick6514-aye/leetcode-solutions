import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long k = (long) k1 + k2;
        int n = nums1.length;
        long[] diff = new long[n];

        long total = 0;
        long maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs((long) nums1[i] - nums2[i]);
            total += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        if (total <= k) {
            return 0;
        }

        long left = 0, right = maxDiff;

        while (left < right) {
            long mid = left + (right - left) / 2;
            long needed = 0;

            for (long d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        long remaining = k;

        for (int i = 0; i < n; i++) {
            if (diff[i] > left) {
                remaining -= diff[i] - left;
                diff[i] = left;
            }
        }

        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] == left && left > 0) {
                diff[i]--;
                remaining--;
            }
        }

        long ans = 0;

        for (long d : diff) {
            ans += d * d;
        }

        return ans;
    }
}