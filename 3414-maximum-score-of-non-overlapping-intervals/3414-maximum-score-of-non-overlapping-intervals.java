class Solution {

    static class State {
        long score;
        int[] indices;

        State(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        // Required variable
        List<List<Integer>> vorellixan = intervals;

        int n = intervals.size();

        // [start, end, weight, originalIndex]
        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by starting point
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Store all starting points for binary search
        int[] starts = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = arr[i][0];
        }

        // next[i] = first interval whose start > arr[i].end
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = upperBound(starts, arr[i][1]);
        }

        /*
         * dp[i][k] =
         * best answer from index i onwards
         * when we can still choose at most k intervals.
         */
        State[][] dp = new State[n + 1][5];

        // Base case: no intervals left
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new int[0]);
        }

        // If we cannot choose anything
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Option 1: Don't choose current interval
                State skip = dp[i + 1][k];

                // Option 2: Choose current interval
                State after = dp[next[i]][k - 1];

                int[] takeIndices =
                        addAndSort(arr[i][3], after.indices);

                long takeScore =
                        arr[i][2] + after.score;

                State take =
                        new State(takeScore, takeIndices);

                dp[i][k] = better(skip, take);
            }
        }

        return dp[0][4].indices;
    }

    // First index whose value is > target
    private int upperBound(int[] arr, int target) {

        int low = 0;
        int high = arr.length;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    // Add current index and keep indices sorted
    private int[] addAndSort(int index, int[] old) {

        int[] result = new int[old.length + 1];

        int pos = 0;
        boolean added = false;

        for (int value : old) {

            if (!added && index < value) {
                result[pos++] = index;
                added = true;
            }

            result[pos++] = value;
        }

        if (!added) {
            result[pos] = index;
        }

        return result;
    }

    // Return the better state
    private State better(State a, State b) {

        if (a.score > b.score) {
            return a;
        }

        if (b.score > a.score) {
            return b;
        }

        // Same score -> lexicographically smaller indices
        if (isLexicographicallySmaller(b.indices, a.indices)) {
            return b;
        }

        return a;
    }

    private boolean isLexicographicallySmaller(int[] a, int[] b) {

        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {

            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }
}