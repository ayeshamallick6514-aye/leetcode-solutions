
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        if ((long) cost1 + cost2 <= costBoth) {
            return (long) cost1 * need1 + (long) cost2 * need2;
        }

        int common = Math.min(need1, need2);
        long ans = (long) common * costBoth;

        need1 -= common;
        need2 -= common;

        if (need1 > 0) {
            ans += (long) need1 * Math.min(cost1, costBoth);
        } else {
            ans += (long) need2 * Math.min(cost2, costBoth);
        }

        return ans;
    }
}
