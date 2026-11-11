
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long a = Math.min(need1, need2);
        long b = Math.max(need1, need2);

        return Math.min(
            Math.min((long) need1 * cost1 + (long) need2 * cost2,
                     a * costBoth + (b - a) * (need1 < need2 ? cost2 : cost1)),
            b * costBoth
        );
    }
}
