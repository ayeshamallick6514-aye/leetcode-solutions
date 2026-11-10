
class Solution {
    public int climbStairs(int n, int[] costs) {
        long a = 0;
        long b = Long.MAX_VALUE / 2;
        long c = Long.MAX_VALUE / 2;

        for (int i = 1; i <= n; i++) {
            long curr = costs[i - 1] + Math.min(
                a + 1,
                Math.min(b + 4, c + 9)
            );

            c = b;
            b = a;
            a = curr;
        }

        return (int) a;
    }
}
