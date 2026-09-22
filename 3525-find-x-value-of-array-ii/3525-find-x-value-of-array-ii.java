import java.util.*;

class Solution {

    class Node {
        int l, r;
        int prod;
        int[] cnt;

        Node(int l, int r) {
            this.l = l;
            this.r = r;
            this.prod = 1;
            this.cnt = new int[k];
        }
    }

    int k;
    Node[] tree;

    // Merge two nodes
    Node merge(Node left, Node right) {
        Node res = new Node(0, 0);

        // Product of complete segment
        res.prod = (left.prod * right.prod) % k;

        // Prefixes completely inside left part
        for (int r = 0; r < k; r++) {
            res.cnt[r] = left.cnt[r];
        }

        // Prefixes that cross from left into right
        for (int r = 0; r < k; r++) {
            int newRemainder = (left.prod * r) % k;
            res.cnt[newRemainder] += right.cnt[r];
        }

        return res;
    }

    // Build Segment Tree
    void build(int node, int l, int r, int[] nums) {

        tree[node] = new Node(l, r);

        if (l == r) {
            int remainder = nums[l] % k;

            tree[node].prod = remainder;
            tree[node].cnt[remainder] = 1;

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        tree[node].l = l;
        tree[node].r = r;
    }

    // Update nums[index]
    void update(int node, int l, int r, int index, int value) {

        if (l == r) {

            int remainder = value % k;

            tree[node].prod = remainder;

            Arrays.fill(tree[node].cnt, 0);
            tree[node].cnt[remainder] = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        Node merged = merge(tree[node * 2], tree[node * 2 + 1]);

        tree[node].prod = merged.prod;
        tree[node].cnt = merged.cnt;
    }

    // Query range [ql, qr]
    Node query(int node, int l, int r, int ql, int qr) {

        // Completely inside
        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) / 2;

        // Completely in left
        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        // Completely in right
        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        // Part left + part right
        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.k = k;

        int n = nums.length;

        tree = new Node[4 * n];

        
        build(1, 0, n - 1, nums);

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            
            update(1, 0, n - 1, index, value);

            
            Node result = query(1, 0, n - 1, start, n - 1);

            
            answer[i] = result.cnt[x];
        }

        return answer;
    }
}