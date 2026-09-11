class Solution {
    public int totalNumbers(int[] digits) {

        boolean[] used = new boolean[1000];
        int count = 0;

        for (int i = 0; i < digits.length; i++) {

            // Hundreds digit cannot be 0
            if (digits[i] == 0) {
                continue;
            }

            for (int j = 0; j < digits.length; j++) {

                // Cannot use the same copy twice
                if (j == i) {
                    continue;
                }

                for (int k = 0; k < digits.length; k++) {

                    // Cannot use the same copy twice
                    if (k == i || k == j) {
                        continue;
                    }

                    // Last digit must be even
                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int num = digits[i] * 100
                            + digits[j] * 10
                            + digits[k];

                    // Count each distinct number only once
                    if (!used[num]) {
                        used[num] = true;
                        count++;
                    }
                }
            }
        }

        return count;
    }
}