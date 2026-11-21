
class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];
        int[] prev = new int[n];

        Arrays.fill(last, -1);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int x = stack.pop();

            if (x < 0) {
                x = ~x;
                last[s.charAt(x) - 'a'] = prev[x];
                continue;
            }

            int ch = s.charAt(x) - 'a';
            prev[x] = last[ch];

            if (last[ch] != -1) {
                newParent[x] = last[ch];
            }

            last[ch] = x;
            stack.push(~x);

            for (int child : children[x]) {
                stack.push(child);
            }
        }

        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }

        int[] order = new int[n];
        int size = 0;

        stack.push(0);

        while (!stack.isEmpty()) {
            int x = stack.pop();
            order[size++] = x;

            for (int child : tree[x]) {
                stack.push(child);
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = n - 1; i >= 0; i--) {
            int x = order[i];

            if (newParent[x] != -1) {
                ans[newParent[x]] += ans[x];
            }
        }

        return ans;
    }
}
