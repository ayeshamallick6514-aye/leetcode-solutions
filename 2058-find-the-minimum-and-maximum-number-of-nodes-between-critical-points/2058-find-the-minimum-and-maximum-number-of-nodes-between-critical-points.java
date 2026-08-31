/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] ans = {-1, -1};

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        int firstCritical = -1;
        int prevCritical = -1;

        int minDistance = Integer.MAX_VALUE;
        int maxDistance = -1;

        while (curr.next != null) {

            ListNode next = curr.next;

            // Check if curr is a critical point
            if ((curr.val > prev.val && curr.val > next.val) ||
                (curr.val < prev.val && curr.val < next.val)) {

                // First critical point
                if (firstCritical == -1) {
                    firstCritical = index;
                }

                // If this is not the first critical point
                if (prevCritical != -1) {
                    minDistance = Math.min(
                        minDistance,
                        index - prevCritical
                    );
                }

                prevCritical = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Less than 2 critical points
        if (firstCritical == prevCritical) {
            return ans;
        }

        // Maximum distance = last critical - first critical
        maxDistance = prevCritical - firstCritical;

        ans[0] = minDistance;
        ans[1] = maxDistance;

        return ans;
    }
}