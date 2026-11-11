
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long a = Math.min(need1, need2);
        long b = Math.max(need1, need2);

        long ans1 = (long) need1 * cost1 + (long) need2 * cost2;
        long ans2 = a * costBoth + (need1 - a) * (long) cost1 + (need2 - a) * (long) cost2;
        long ans3 = b * costBoth;

        return Math.min(ans1, Math.min(ans2, ans3));
    }
}
